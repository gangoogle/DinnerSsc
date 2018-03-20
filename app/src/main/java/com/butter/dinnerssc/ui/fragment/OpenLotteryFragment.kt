package com.butter.dinnerssc.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.dinnerssc.net.RetrofitNetHelper
import com.butter.dinnerssc.R
import com.butter.dinnerssc.data.getTrendItemData
import com.butter.dinnerssc.model.event.InitDataEvent
import com.butter.dinnerssc.model.event.SwitchLotteryEvent
import com.butter.dinnerssc.model.response.BaseResp
import com.butter.dinnerssc.model.response.CaiPiaoResponse
import com.butter.dinnerssc.net.Api
import com.butter.dinnerssc.ui.activity.LotteryDetailActivity
import kotlinx.android.synthetic.main.fragment_open_lotterry.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlinx.android.synthetic.main.fragment_open_lotterry.view.*

/**
 * Created by zgyi on 2018-02-07.
 */
class OpenLotteryFragment : BaseFragment() {

    val SIZE = 1

    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun initViewData() {
    }

    override fun setClick(view: View?) {
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_open_lotterry, container, false)
        return view
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRevInitDataEvent(initDataEvent: InitDataEvent) {
        requestData(0)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRevSwitchLottery(switchEvent: SwitchLotteryEvent) {
        requestData(switchEvent.position)
    }

    fun requestData(position: Int) {
        val callResponse = RetrofitNetHelper.getInstance(context)
                .getAPIService(Api::class.java)
                .requestUrl("${getTrendItemData()[position].url}-$SIZE.json")
        RetrofitNetHelper.getInstance(context)
                .enqueueCall(callResponse, object : RetrofitNetHelper.RetrofitCallBack<CaiPiaoResponse> {
                    override fun onSuccess(baseResp: BaseResp<CaiPiaoResponse>) {
                        if (baseResp == null || baseResp.data == null || baseResp.data.size <= 0) {
                            return
                        }
                        mView?.ll_container?.visibility = View.VISIBLE
                        mView?.tv_lottery_number?.text =
                                baseResp.data[0].openCode.replace(',', ' ', true)
                        mView?.tv_odd_number?.text = baseResp.data[0].opentimestamp
                        mView?.tv_time?.text = baseResp.data[0].opentime
                        mView?.setOnClickListener {
                            val intent = Intent(context, LotteryDetailActivity::class.java)
                            intent.putExtra("url", getTrendItemData()[position]?.url)
                            intent.putExtra("name",  getTrendItemData()[position]?.name)
                            context.startActivity(intent)
                        }
                    }

                    override fun onFailure(error: String) {
                        mView?.ll_container?.visibility = View.INVISIBLE
                    }
                })
    }
}
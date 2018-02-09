package com.butter.dinnerssc.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.dinnerssc.R
import com.butter.dinnerssc.data.getTrendDetailData
import com.butter.dinnerssc.data.getTrendItemData
import com.butter.dinnerssc.model.AnnularModel
import com.butter.dinnerssc.model.event.InitDataEvent
import com.butter.dinnerssc.model.event.SwitchLotteryEvent
import com.butter.dinnerssc.ui.activity.TrendChartActivity
import kotlinx.android.synthetic.main.fragment_dimens.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlinx.android.synthetic.main.fragment_dimens.view.*

/**
 * Created by zgyi on 2018-02-08.
 */
class DimensFragment : BaseFragment() {
    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_dimens, container, false)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitDataEvent(event: InitDataEvent) {
        initData(0)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvSwitchDataEvent(event: SwitchLotteryEvent) {
        initData(event.position)
    }


    override fun initViewData() {
    }

    fun initData(position: Int) {
        val item = getTrendDetailData()[getTrendItemData()[position].url]
        val list = item?.dimens
        val sum = list!![0].percent + list!![1].percent + list!![2].percent
        mView?.aa_view?.setData(list!!, sum)
        mView?.tv_a?.text = "前驱走势：    ${list!![0].percent}%"
        mView?.tv_b?.text = "个位：    ${list!![1].percent}%"
        mView?.tv_c?.text = "百位：    ${list!![2].percent}%"
    }


    override fun setClick(view: View?) {
        mView?.tv_chart?.setOnClickListener {
            startActivity(Intent(context, TrendChartActivity::class.java))
        }
    }
}
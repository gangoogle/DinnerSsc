package com.butter.yapplet.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapplet.R
import com.butter.yapplet.caiyi.lottery.LottoTrendActivity
import com.butter.yapplet.data.getTrendDetailData
import com.butter.yapplet.data.getTrendItemData
import com.butter.yapplet.model.PopListItem
import com.butter.yapplet.model.event.InitDataEvent
import com.butter.yapplet.model.event.SwitchLotteryEvent
import com.butter.yapplet.ui.customview.ListPopupWindow
import com.butter.yapplet.ui.customview.MyProgressDialog
import com.butter.yapplet.utils.ComUtils
import kotlinx.android.synthetic.main.fragment_indicator_show.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * Created by zgyi on 2017-11-24.
 */
class IndicatorShowFragment : BaseFragment() {

    lateinit var mLotterListPopw: ListPopupWindow
    var mLotterLists: MutableList<PopListItem> = mutableListOf()
    var currentLotteryIndex: Int = 0
    lateinit var dialog: MyProgressDialog

    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_indicator_show, container, false)
        mLotterListPopw = ListPopupWindow(context, true, 0, 1)
        dialog = MyProgressDialog(context)
        return view!!
    }

    override fun initViewData() {
        //设置日期
        mView?.tv_date?.text = "昨日：${ComUtils.getYesterday(Date())}"
        getTrendItemData().forEach { mLotterLists.add(PopListItem(it.name, it.imgId)) }
        mLotterListPopw.setData(mLotterLists)

    }


    override fun setClick(view: View?) {
        view?.iv_switch_lottery?.setOnClickListener {
            mLotterListPopw.showAsDropDown(currentLotteryIndex, it)
        }
        mLotterListPopw.addOnPopupItemClickListener {
            if (currentLotteryIndex != it) {
                currentLotteryIndex = it
                changeLottery()
            }
        }
        view?.tv_detail?.setOnClickListener {
            context?.startActivity(Intent(context, LottoTrendActivity::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitData(event: InitDataEvent) {
        loadData()
    }

    fun changeLottery() {
        //发送延迟请求
        val t = Random().nextInt(3)
        dialog.initDialog("")
        val msg = Message()
        msg.what = 0
        handler.sendMessageDelayed(msg, 1000 * t.toLong())

    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    EventBus.getDefault().post(SwitchLotteryEvent(currentLotteryIndex))
                    loadData()
                    dialog.dissmisDialog()
                }
            }
        }
    }

    fun loadData() {
        val url = getTrendItemData()[currentLotteryIndex].url
        mView?.tv_budget?.text = "预算：${getTrendDetailData()[url]?.budgetMoney}万"
        mView?.tv_chuzhulu?.text = "出注率：${getTrendDetailData()[url]?.occuRate}%"
        mView?.tv_forcast?.text = "预测：${getTrendDetailData()[url]?.forceasRate}%"
        mView?.ap_view?.setPercent(getTrendDetailData()[url]?.archPercent!!)
    }
}
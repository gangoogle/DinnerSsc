package com.butter.yapplet.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapplet.R
import com.butter.yapplet.adapter.PerfTargetAdapter
import com.butter.yapplet.model.LinePercentData
import com.butter.yapplet.model.event.InitDataEvent
import com.butter.yapplet.ui.activity.NoticeActivity
import kotlinx.android.synthetic.main.fragment_line.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeLineFragment : BaseFragment(){
    override fun initViewData() {
    }

    override fun setClick(view: View?) {
        view?.setOnClickListener { startActivity(Intent(context, NoticeActivity::class.java)) }
    }

    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_line, container, false)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitDataEvent(event: InitDataEvent) {
        initData()
    }

    fun initData(){
        val msg = Message()
        msg.what = 0
        handler.sendMessageDelayed(msg, 2000)
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    mView?.listview?.adapter= PerfTargetAdapter(context,  listOf(
                            LinePercentData("聚类分析",-98,false),
                            LinePercentData("冷数据存储",150,false),
                            LinePercentData("聚合",120,false),
                            LinePercentData("并联分析",112,false)
                    ))
                }
            }
        }
    }

}
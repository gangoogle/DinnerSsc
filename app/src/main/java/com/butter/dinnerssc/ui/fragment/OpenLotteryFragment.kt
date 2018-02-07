package com.butter.dinnerssc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.dinnerssc.R
import com.butter.dinnerssc.model.event.InitDataEvent
import com.butter.dinnerssc.model.event.SwitchLotteryEvent
import kotlinx.android.synthetic.main.fragment_open_lotterry.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlinx.android.synthetic.main.fragment_open_lotterry.view.*

/**
 * Created by zgyi on 2018-02-07.
 */
class OpenLotteryFragment : BaseFragment() {
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

    fun onRevSwitchLottery(switchEvent: SwitchLotteryEvent) {
        requestData(switchEvent.position)
    }

    fun requestData(position: Int) {
       mView?.ll_container?.visibility = View.VISIBLE
    }
}
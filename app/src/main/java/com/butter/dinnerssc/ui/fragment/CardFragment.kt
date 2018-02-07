package com.butter.dinnerssc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.dinnerssc.R
import com.butter.dinnerssc.data.getTrendDetailData
import com.butter.dinnerssc.data.getTrendItemData
import com.butter.dinnerssc.model.event.InitDataEvent
import com.butter.dinnerssc.model.event.SwitchLotteryEvent
import kotlinx.android.synthetic.main.fragment_card.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by zgyi on 2018-02-07.
 */
class CardFragment : BaseFragment() {

    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_card, container, false)
        return view
    }

    override fun initViewData() {
    }

    override fun setClick(view: View?) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitData(event: InitDataEvent) {
        loadData(0)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun switchLotteryEvent(event: SwitchLotteryEvent) {
        loadData(event.position)
    }


    fun loadData(position: Int) {
        val item = getTrendDetailData()[getTrendItemData()[position].url]
        mView?.tv_id_1?.setText("${item?.v1}")
        mView?.tv_id_2?.setText("${item?.v2}")
        mView?.tv_id_3?.setText("${item?.v3}")
        mView?.tv_id_4?.setText("${item?.v4}")
        mView?.tv_id_5?.setText("${item?.v5}")
        mView?.tv_id_6?.setText("${item?.v6}")
    }
}
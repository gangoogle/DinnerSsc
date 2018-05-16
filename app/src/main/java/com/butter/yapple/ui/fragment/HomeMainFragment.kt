package com.butter.yapple.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.butter.yapple.model.event.InitDataEvent
import kotlinx.android.synthetic.main.fragment_current_region.*
import layout.NoticeFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by zgyi on 2017-11-24.
 */
class HomeMainFragment : BaseHomeFragment() {

    override fun initFragmentView(): MutableList<BaseFragment> {
        val noticeFgm: BaseFragment = NoticeFragment()
        val indicatorShowFgm: BaseFragment = IndicatorShowFragment()
        val cardFgm: BaseFragment = CardFragment()
        val openLottery: BaseFragment = OpenLotteryFragment()
        val dimensFgm: BaseFragment = DimensFragment()
        val lineFgm: HomeLineFragment = HomeLineFragment()
        return mutableListOf<BaseFragment>(noticeFgm, dimensFgm, lineFgm, indicatorShowFgm, cardFgm, openLottery)
    }

}
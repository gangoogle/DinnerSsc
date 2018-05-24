package com.butter.yapplet.ui.fragment

import layout.NoticeFragment

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
package com.butter.dinnerssc.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_current_region.*

/**
 * Created by zgyi on 2017-11-24.
 */
class HomeMainFragment : BaseHomeFragment() {


    override fun initFragmentView(): MutableList<BaseFragment> {
        val indicatorShowFgm: BaseFragment = IndicatorShowFragment()
        return mutableListOf<BaseFragment>(indicatorShowFgm)
    }

}
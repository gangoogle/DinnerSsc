package com.butter.yapplet.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapplet.R

/**
 * Created by zgyi on 2017-11-24.
 */
open abstract class BaseHomeFragment : Fragment() {
    var mActivity: Activity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mActivity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layoutId = 0
        if (this is HomeMainFragment) {
            layoutId = R.layout.fragment_current_region
        } else if (this is HomeSoccerFragment) {
            layoutId = R.layout.fragment_home_subordinate
        } else if (this is HomeChartFragment) {
            layoutId = R.layout.fragment_home_chart
        } else if (this is HomeMeFragment) {
            layoutId = R.layout.fragment_home_me
        }
        val view = inflater?.inflate(layoutId, container, false)
        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var id: Int = 0
        if (this is HomeMainFragment) {
            id = R.id.ll_fgm_container
        } else if (this is HomeSoccerFragment) {
            id = R.id.ll_fgm_container_sub
        } else if (this is HomeChartFragment) {
            id = R.id.ll_fgm_home_chart
        } else if (this is HomeMeFragment) {
            id = R.id.ll_fgm_home_me
        }
        val fragments = initFragmentView()

        val manager = activity.supportFragmentManager
        val transaction = manager.beginTransaction()
        for (fragment in fragments) {
            transaction.add(id, fragment)
        }
        transaction.commit()
    }

    abstract fun initFragmentView(): MutableList<BaseFragment>
}
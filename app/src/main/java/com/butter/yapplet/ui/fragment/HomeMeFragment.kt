package com.butter.yapplet.ui.fragment

/**
 * Created by zgyi on 2017-11-24.
 */
class HomeMeFragment : BaseHomeFragment() {

    override fun initFragmentView(): MutableList<BaseFragment> {
        return mutableListOf<BaseFragment>(MeFragment())
    }
}
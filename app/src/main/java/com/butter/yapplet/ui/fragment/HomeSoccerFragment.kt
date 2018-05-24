package com.butter.yapplet.ui.fragment

/**
 * Created by gangoogle on 2018/3/16.
 */
class HomeSoccerFragment :BaseHomeFragment(){
    override fun initFragmentView(): MutableList<BaseFragment> {
        return mutableListOf<BaseFragment>(SoccerLotteyFragment())
    }
}
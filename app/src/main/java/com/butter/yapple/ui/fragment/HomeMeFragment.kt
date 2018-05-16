package com.butter.yapple.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zgyi on 2017-11-24.
 */
class HomeMeFragment : BaseHomeFragment() {

    override fun initFragmentView(): MutableList<BaseFragment> {
        return mutableListOf<BaseFragment>(MeFragment())
    }
}
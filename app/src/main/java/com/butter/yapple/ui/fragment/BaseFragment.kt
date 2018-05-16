package com.butter.yapple.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View


/**
 * Created by zgyi on 2017-11-24.
 */
open abstract class BaseFragment : Fragment() {
    var mActivity: Activity? = null
    var mView: View? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mActivity = context as Activity
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        initViewData()
        setClick(view)
    }

    protected abstract fun initViewData()

    protected abstract fun setClick(view: View?)
}
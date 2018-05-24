package com.butter.yapplet.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapplet.R
import com.butter.yapplet.adapter.RCTrendAdapter
import com.butter.yapplet.data.getTrendItemData
import com.butter.yapplet.model.event.InitThreePape
import com.butter.yapplet.model.event.InitTwoPape
import com.butter.yapplet.model.response.BaseNewsResponse
import com.butter.yapplet.model.response.NewsResponse
import com.butter.yapplet.net.Api
import com.butter.yapplet.net.RetrofitNetHelper
import com.butter.yapplet.ui.adapter.RCNewsAdapter
import com.butter.yapplet.ui.customview.MyProgressDialog
import com.butter.yapplet.utils.Constant
import kotlinx.android.synthetic.main.fragment_chart.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlinx.android.synthetic.main.fragment_chart.view.*

/**
 * Created by gangoogle on 2018/3/16.
 */
class ChartFragment : BaseFragment() {
    lateinit var dialog: MyProgressDialog
    var isLoad = false

    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_chart, container, false)
        return view
    }

    override fun initViewData() {
    }

    override fun setClick(view: View?) {
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitClickEvent(event: InitThreePape) {
        if (isLoad == false) {
            initData()
        }
    }


    fun initData() {
        Log.d("yzg", "trend view initData")
        view?.rc_view?.layoutManager = GridLayoutManager(context, 2)
        view?.rc_view?.adapter = RCTrendAdapter(context, getTrendItemData())
    }
}
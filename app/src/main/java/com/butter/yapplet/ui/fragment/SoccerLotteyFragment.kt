package com.butter.yapplet.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapplet.R
import com.butter.yapplet.model.event.InitTwoPape
import com.butter.yapplet.model.response.BaseNewsResponse
import com.butter.yapplet.model.response.NewsResponse
import com.butter.yapplet.net.Api
import com.butter.yapplet.net.RetrofitNetHelper
import com.butter.yapplet.ui.adapter.RCNewsAdapter
import com.butter.yapplet.ui.customview.MyProgressDialog
import com.butter.yapplet.utils.Constant.JDNEWS_APPKEY
import com.butter.yapplet.utils.Constant.NEWS_URL
import kotlinx.android.synthetic.main.fragment_socccer.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by gangoogle on 2018/3/16.
 */
class SoccerLotteyFragment : BaseFragment() {
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
        val view = inflater?.inflate(R.layout.fragment_socccer, container, false)
        return view
    }

    override fun initViewData() {
        dialog = MyProgressDialog(context)
    }

    override fun setClick(view: View?) {
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitClickEvent(event: InitTwoPape) {
        if (isLoad == false) {
            initData()
        }
    }


    fun initData() {
        dialog.initDialog("加载中")
        val callResponse = RetrofitNetHelper.getInstance(context)
                .getAPIService(Api::class.java)
                .requestNews(NEWS_URL, "财经", "40", "0", JDNEWS_APPKEY)
        RetrofitNetHelper.getInstance(context)
                .enqueueNewsCall(callResponse, object : RetrofitNetHelper.RetrofitNewsCallBack<NewsResponse> {
                    override fun onSuccess(baseResp: BaseNewsResponse<NewsResponse>) {
                        dialog.dissmisDialog()
                        isLoad = true
                        //去除pic为空的数据
                        val arr = arrayListOf<NewsResponse.News>()
                        baseResp.result.result.list
                                .filter { !it.pic.equals("") }
                                .forEach { arr.add(it) }
                        rc_view?.layoutManager = LinearLayoutManager(context)
                        rc_view?.adapter = RCNewsAdapter(context, arr)

                    }

                    override fun onFailure(error: String) {
                        dialog.dissmisDialog()
                        Snackbar.make(rc_view!!, error, Snackbar.LENGTH_SHORT).show()
                        rc_view?.adapter = RCNewsAdapter(context, arrayListOf<NewsResponse.News>())
                    }
                })
    }
}
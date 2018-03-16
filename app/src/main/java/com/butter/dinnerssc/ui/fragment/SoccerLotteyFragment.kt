package com.butter.dinnerssc.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.butter.dinnerssc.R
import com.butter.dinnerssc.model.event.InitDataEvent
import kotlinx.android.synthetic.main.fragment_socccer.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlinx.android.synthetic.main.fragment_socccer.view.*

/**
 * Created by gangoogle on 2018/3/16.
 */
class SoccerLotteyFragment : BaseFragment() {

    init {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webviewSet()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_socccer, container, false)
        return view
    }

    override fun initViewData() {
    }

    override fun setClick(view: View?) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recvInitDataEvent(event: InitDataEvent) {
        initData()
    }

    fun webviewSet() {
        webview.settings.setSupportZoom(false)
        webview.settings.builtInZoomControls = false
        try {
            webview.settings.javaScriptEnabled = true
        } catch (e: Exception) {
            println(e)
        }

        webview.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webview.settings.cacheMode = WebSettings.LOAD_DEFAULT  //设置 缓存模式
        // 开启 DOM storage API 功能
        webview.settings.domStorageEnabled = true
        //开启 database storage API 功能
        webview.settings.databaseEnabled = true
        val cacheDirPath = context.getCacheDir().getAbsolutePath()
        //设置  Application Caches 缓存目录
        webview.settings.setAppCachePath(cacheDirPath)
        //开启 Application Caches 功能
        webview.settings.setAppCacheEnabled(true)
        webview.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != "") {
                    view?.loadUrl(url);   //在当前的webview中跳转到新的url
                    System.out.println("url:" + url);
                }
                return true;
            }
        })
    }

    fun initData() {
        webview.loadUrl("http://live.m.500.com/home/zq/jczq/cur?render=local")
    }
}
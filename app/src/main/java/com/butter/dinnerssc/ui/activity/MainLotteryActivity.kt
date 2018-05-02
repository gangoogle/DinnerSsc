package com.butter.dinnerssc.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_main_lottery.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.butter.dinnerssc.R


class MainLotteryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_lottery)
        if (intent == null || !intent.hasExtra("url")) {
            return
        }
        val url = intent.getStringExtra("url")
        Log.d("yzg","url:$url")
        webviewSet()
        webview.loadUrl("$url")
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
        val cacheDirPath = filesDir.absolutePath + "/webCache"
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

    override fun onBackPressed() {
        if (null != webview && webview.canGoBack()) {
            webview.goBack()
        } else {
            webview.loadUrl("about:blank")
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.getRepeatCount() == 0) {
            webview.loadUrl("about:blank")
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

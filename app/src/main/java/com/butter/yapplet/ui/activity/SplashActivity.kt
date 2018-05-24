package com.butter.yapplet.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.butter.yapplet.R
import com.butter.yapplet.utils.ComUtils
import com.butter.yapplet.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONObject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {
    private lateinit var mContext: Context
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreen_content_controls.visibility = View.VISIBLE
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_splash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        // Set up the user interaction to manually show or hide the system UI.

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        checkServerByYan(false)
    }

    fun checkServerByYan(isDebug: Boolean) {
        if (!isDebug) {
            //360
            val appId: String = "1804171045"
            val requestQueue = Volley.newRequestQueue(this)
            val url: String =
                    "http://app.27305.com/appid.php?appid=$appId"
            Log.d("yzg", url)
            val request = StringRequest(url, Response.Listener<String> {
                Log.d("yzg", "checkServer:Result：$it")
                val jsonObj = JSONObject(it)
                if (jsonObj.getInt("status") == 1) {
                    if (jsonObj.getString("isshowwap").equals("1")) {
                        val msg = Message()
                        msg.obj = jsonObj.getString("wapurl")
                        msg.what = 1
                        handler.sendMessage(msg)
                    } else {
                        handler.sendEmptyMessage(0)
                    }
                } else {
                    handler.sendEmptyMessage(0)
                }
            }, Response.ErrorListener {
                handler.sendEmptyMessage(0)
            })
            requestQueue.add(request)
        } else {
            //需要跳转
            val msg = Message()
            msg.obj = "http://www.baidu.com"
            msg.what = 1
            handler.sendMessage(msg)
        }
    }
//
//    fun checkServer(isDebug:Boolean) {
//        if(!isDebug) {
//            val appId: String = "yzg002"
//            val requestQueue = Volley.newRequestQueue(this)
//            val url: String =
//                    "http://5597755.com/Lottery_server/get_init_data.php?type=android&appid=$appId"
//            Log.d("yzg", url)
//            val request = StringRequest(url, Response.Listener<String> {
//                Log.d("yzg", "checkServer:Result：$it")
//                val jsonObj = JSONObject(it)
//                if (jsonObj.has("rt_code")
//                        && jsonObj.getString("rt_code").equals("200")
//                        && jsonObj.has("data")) {
//                    val base64Data = jsonObj.getString("data")
//                    if (!TextUtils.isEmpty(base64Data)) {
//                        var data: String = ""
//                        try {
//                            data = Base64Util.decode(base64Data)
//                        } catch (e: Exception) {
//                            handler.sendEmptyMessage(0)
//                        }
//                        Log.d("yzg", "data:${data}")
//                        val dataObj = JSONObject(data)
//                        if (dataObj.get("show_url").equals("1")) {
//                            //需要跳转
//                            val msg = Message()
//                            msg.obj = dataObj.getString("url")
//                            msg.what = 1
//                            handler.sendMessage(msg)
//                        } else {
//                            handler.sendEmptyMessage(0)
//                        }
//                    } else {
//                        handler.sendEmptyMessage(0)
//                    }
//                } else {
//                    handler.sendEmptyMessage(0)
//                }
//
//            }, Response.ErrorListener {
//                handler.sendEmptyMessage(0)
//            })
//            requestQueue.add(request)
//        }else{
//            //需要跳转
//            val msg = Message()
//            msg.obj = "http://www.baidu.com"
//            msg.what = 1
//            handler.sendMessage(msg)
//        }

//    }


    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    Toast.makeText(mContext, "当前为最新版", Toast.LENGTH_SHORT).show()
                    if (!ComUtils.getLoginInfo(mContext).email.equals("")) {
                        startActivity(Intent(mContext, MainActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(mContext, LoginActivityFix::class.java))
                        finish()
                    }
                }
                1 -> {
                    val intent = Intent(mContext, WebViewActivity::class.java)
                    intent.putExtra("url", msg.obj as String)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreen_content_controls.visibility = View.GONE
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
}

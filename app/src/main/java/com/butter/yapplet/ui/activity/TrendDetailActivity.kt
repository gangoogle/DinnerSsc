package com.butter.yapplet.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.butter.yapplet.R
import com.butter.yapplet.ui.customview.MyProgressDialog
import com.butter.yapplet.utils.ComUtils
import kotlinx.android.synthetic.main.activity_trend_detail.*
import java.util.*

class TrendDetailActivity : AppCompatActivity() {

    lateinit var dialog: MyProgressDialog
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_trend_detail)
        dialog = MyProgressDialog(this)
        val bundle = intent.getBundleExtra("data")
        val title = bundle.getString("name")
        tv_title_t.text = title
        fab.setOnClickListener { finish() }
        val t = Random().nextInt(3)
        dialog.initDialog("加载中...")
        val msg = Message()
        msg.obj = bundle
        msg.what = 0
        handler.sendMessageDelayed(msg, 1000 * t.toLong())
        ComUtils.addScore(this, 4)
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    dialog.dissmisDialog()
                    webview.loadHtml((msg?.obj as Bundle).getString("url"))
                }
            }
        }
    }
}

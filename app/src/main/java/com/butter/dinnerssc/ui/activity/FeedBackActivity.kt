package com.butter.dinnerssc.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.widget.Toast
import com.butter.dinnerssc.R
import com.butter.dinnerssc.ui.customview.MyProgressDialog

import kotlinx.android.synthetic.main.activity_feed_back.*
import java.util.*

class FeedBackActivity : AppCompatActivity() {
    private lateinit var dialog: MyProgressDialog
    private lateinit var ctx: Context;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)
        dialog = MyProgressDialog(this)
        ctx = this
        iv_back_b.setOnClickListener { finish() }
        bt_sure.setOnClickListener {
            if (TextUtils.isEmpty(et_advice.text)) {
                Toast.makeText(this, "建议不能为空", Toast.LENGTH_SHORT).show()
            } else {
                val t = Random().nextInt(3)
                dialog.initDialog("加载中...")
                val msg = Message()
                msg.what = 0
                handler.sendMessageDelayed(msg, 1000 * t.toLong())
            }
        }
    }


    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    dialog.dissmisDialog()
                    Toast.makeText(ctx, "提交成功", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}

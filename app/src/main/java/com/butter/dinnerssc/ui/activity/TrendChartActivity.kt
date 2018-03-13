package com.butter.dinnerssc.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.butter.dinnerssc.R
import com.butter.dinnerssc.model.event.SwitchLotteryEvent
import com.butter.dinnerssc.ui.customview.MyProgressDialog
import kotlinx.android.synthetic.main.activity_trend_chart.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class TrendChartActivity : AppCompatActivity() {

    lateinit var dialog: MyProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trend_chart)
        webview.loadHtml("trend.html")
        dialog = MyProgressDialog(this)
        dialog.initDialog("")
        //发送延迟请求
        val t = Random().nextInt(3)
        val msg = Message()
        msg.what = 0
        handler.sendMessageDelayed(msg, 1000 * t.toLong())
    }


    fun loadData(){
        webview.loadJavaScript("showTrendChart",this.getString(R.string.chart_json))
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    loadData()
                    dialog.dissmisDialog()
                }
            }
        }
    }

}



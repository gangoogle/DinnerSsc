package com.butter.yapplet.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.butter.yapplet.R
import com.butter.yapplet.ui.customview.MyProgressDialog
import kotlinx.android.synthetic.main.activity_trend_chart.*
import java.util.*

class TrendChartActivity : AppCompatActivity() {
    val chartJson = """ {"barData":{"color":"#B5CFFA","data":[124.0,132.0,114.0,106.0,90.0,111.0,131.0,134.0,121.0,95.0,93.0,64.0,81.0,95.0,124.0],"dataStr":null,"lineWidth":1,"name":"预测"},"date":["12/06","12/07","12/08","12/09","12/10","12/11","12/12","12/13","12/14","12/15","12/16","12/17","12/18","12/19","12/20"],"holidayChartData":{"color":"#4070ec","dataStr":["","","","","","","","","","","","","","",""],"name":"节日"},"hzLine":{"color":"#FF7D23","data":[266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0,266.0],"dataStr":null,"lineWidth":1,"name":"可卖票数"},"legend":["预测","可卖票数","已有","预算","去年同期已有","去年同期实际"],"lineData":[{"color":"#4070ec","data":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"dataStr":null,"lineWidth":0,"name":"记事本"},{"color":"#4070ec","data":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"dataStr":null,"lineWidth":0,"name":"事件"},{"color":"#FF5081","data":[111.0,90.0,50.0,45.0,32.0,38.0,51.0,78.0,28.0,16.0,7.0,7.0,4.0,5.0,23.0],"dataStr":null,"lineWidth":1,"name":"已有"},{"color":"#9E7DF6","data":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"dataStr":null,"lineWidth":1,"name":"预算"},{"color":"#84BB0C","data":[129.0,106.0,33.0,15.0,14.0,13.0,13.0,42.0,41.0,37.0,7.0,6.0,5.0,5.0,5.0],"dataStr":null,"lineWidth":1,"name":"去年同期已有"},{"color":"#00b1c6","data":[129.0,132.0,87.0,104.0,60.0,120.0,114.0,119.0,117.0,83.0,91.0,50.0,111.0,117.0,109.0],"dataStr":null,"lineWidth":1,"name":"去年同期实际"}],"observeDate":0,"weeks":["Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed"]}"""
    lateinit var dialog: MyProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trend_chart)
        //onClick
        fab_l.setOnClickListener{ finish() }
        //webview
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
        Log.d("yzg",chartJson)
        webview.loadJavaScript("showTrendChart",chartJson)
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



package com.butter.yapplet.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.butter.yapplet.R
import kotlinx.android.synthetic.main.activity_notice.*
class NoticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        ll_title.setOnClickListener { finish() }
    }
}

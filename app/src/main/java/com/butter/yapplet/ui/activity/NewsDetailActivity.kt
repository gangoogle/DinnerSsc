package com.butter.yapplet.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.butter.yapplet.R
import com.butter.yapplet.utils.ComUtils

import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {
    var isCollection = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ll_status_t.setBackgroundColor(this.resources.getColor(R.color.cadetblue))
//            this.getWindow().setStatusBarColor(this.resources.getColor(R.color.cadetblue))
//            tv_title_t.setTextColor(this.resources.getColor(R.color.white))
//            ll_status_t.setPadding(20,10,20,10)
//        }
        val strContent = intent.getStringExtra("content")
        val title = intent.getStringExtra("title")
        val src = intent.getStringExtra("src")
        val time = intent.getStringExtra("time")
//        tv_title_t.text = title
//        tv_title_t.gravity=Gravity.CENTER_VERTICAL
//        tv_title_t.setTextSize(14f)
        my_web.loadData(strContent)
        tv_title_a.text = title
        tv_src.text = src
        tv_time.text = time
        ComUtils.getCollections(this).forEach {
            if (it.equals(title)) {
                isCollection = true
                iv_collection.setImageResource(R.drawable.ic_favorite_black_24dp)
            }
        }
        iv_collection.setOnClickListener {
            if (!isCollection) {
                isCollection = !isCollection
                ComUtils.saveCollection(this, title)
                iv_collection.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                isCollection = !isCollection
                ComUtils.deleteCollection(this, title)
                iv_collection.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }
        }

        ComUtils.addScore(this, 10)
    }

    private fun getHtmlData(bodyHTML: String): String {
        val head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>"
        return "<html>$head<body>$bodyHTML</body></html>"
    }
}

package com.butter.yapplet.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.butter.yapplet.R
import com.butter.yapplet.model.response.NewsResponse
import com.butter.yapplet.ui.activity.NewsDetailActivity
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Created by zgyi on 2018-01-05.
 */
class RCNewsAdapter(val context: Context, val newsArray: ArrayList<NewsResponse.News>) : RecyclerView.Adapter<RCNewsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Glide.with(context).load(newsArray[position].pic)
                .centerCrop()
                .crossFade()
                .error(R.mipmap.no_picture)
                .into(holder?.itemView?.iv_pic)
        holder?.itemView?.tv_title?.text = newsArray[position].title
        holder?.itemView?.tv_time?.text = newsArray[position].time
        holder?.itemView?.setOnClickListener {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("content", newsArray[position].content)
            intent.putExtra("title", newsArray[position].title)
            intent.putExtra("src", newsArray[position].src)
            intent.putExtra("time", newsArray[position].time)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsArray.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
package com.butter.yapple.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapple.R
import kotlinx.android.synthetic.main.item_collection.view.*

/**
 * Created by zgyi on 2018-01-18.
 */
class RCCollectionAdapter(val context: Context, val newsArray: ArrayList<String>) : RecyclerView.Adapter<RCCollectionAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.tv_collection?.text = newsArray[position];

    }

    override fun getItemCount(): Int {
        return newsArray.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_collection, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
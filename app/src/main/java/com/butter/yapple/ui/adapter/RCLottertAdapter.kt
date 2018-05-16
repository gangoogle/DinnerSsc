package com.butter.yapple.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.yapple.R
import com.butter.yapple.model.response.CaiPiaoResponse
import kotlinx.android.synthetic.main.item_lottery_info.view.*

/**
 * Created by zgyi on 2017-12-28.
 */
class RCLottertAdapter(val context: Context, val lotterts: ArrayList<CaiPiaoResponse>)
    : RecyclerView.Adapter<RCLottertAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.view?.tv_lottery_number?.text =
                lotterts[position].openCode.replace(',', ' ', true)
        holder?.view?.tv_odd_number?.text = lotterts[position].opentimestamp
        holder?.view?.tv_time?.text = lotterts[position].opentime
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lottery_info, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lotterts.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}
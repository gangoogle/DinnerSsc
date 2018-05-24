package com.butter.yapplet.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.butter.yapplet.R
import com.butter.yapplet.model.LinePercentData
import kotlinx.android.synthetic.main.item_perf_target.view.*

/**
 * Created by zgyi on 2017-11-30.
 * 业绩目标适配器
 */
class PerfTargetAdapter(val context: Context, val items: List<LinePercentData>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var view: View? = null
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_perf_target, null)
            viewHolder = ViewHolder(view)
            view.setTag(viewHolder)
        } else {
            view = convertView
            viewHolder = view.getTag() as ViewHolder
        }
        viewHolder.view.tv_des.text = items[position].des
        //无数据显示--
        if (items[position].noData) {
            viewHolder.view.tv_value.text = ""
        } else {
            viewHolder.view.tv_value.text = "${items[position].percent}"
        }
        viewHolder.view.lp_view.setPercent(items[position].percent, !items[position].noData)
        return view!!
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.size
    }

    class ViewHolder(val view: View)
}
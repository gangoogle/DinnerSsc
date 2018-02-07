package com.butter.dinnerssc.data

import com.butter.dinnerssc.R
import com.butter.dinnerssc.model.TrendItem


/**
 * Created by zgyi on 2018-01-03.
 */
fun getTrendItemData(): List<TrendItem> {
    return listOf(
            TrendItem(R.mipmap.ic_7lecai, "七乐彩", "daletou.html", "qlc"),
            TrendItem(R.mipmap.ic_shuangseqiu, "双色球", "shuangseqiu.html", "ssq"),
            TrendItem(R.mipmap.ic_daletou, "大乐透", "daletou.html", "dlt"),
            TrendItem(R.mipmap.ic_3d, "福彩3d", "3dzoushi.html", "fc3d"),
            TrendItem(R.mipmap.ic_11xuan5, "11选5", "11xuan5.html", "bj11x5"),
            TrendItem(R.mipmap.ic_7xingcai, "7星彩", "7xingcai.html", "qxc"),
            TrendItem(R.mipmap.ic_kuai3, "快3", "kuai3.html", "bjk3"),
            TrendItem(R.mipmap.ic_pailie3, "排列3", "pailie3.html", "pl3"),
            TrendItem(R.mipmap.ic_pailie5, "排列5", "pailie5.html", "pl5")
    )
}
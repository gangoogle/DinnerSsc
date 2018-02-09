package com.butter.dinnerssc.data

import android.graphics.Color
import com.butter.dinnerssc.model.AnnularModel
import com.butter.dinnerssc.model.LinePercentData
import com.butter.dinnerssc.model.TrendDetailItem

/**
 * Created by zgyi on 2018-01-23.
 */

fun getTrendDetailData(): Map<String, TrendDetailItem> {
    return mapOf(
            "qlc" to TrendDetailItem(121f,65.2f,30f,12f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",-60,false),
                            LinePercentData("六等奖",160,false)
                            )
            ,132,211,342,322,411,162
            , listOf(
                    AnnularModel(63, Color.parseColor("#44a4f7")),
                    AnnularModel(22, Color.parseColor("#ffc90e")),
                    AnnularModel(15, Color.parseColor("#c862d7")),
                    AnnularModel(5, Color.parseColor("#3d76ea")),
                    AnnularModel(5, Color.parseColor("#4fd9fe"))
                    )
            ),
            "ssq" to TrendDetailItem(266f,80f,90f,40f,
                    listOf(
                            LinePercentData("一等奖",-98,false),
                            LinePercentData("二等奖",150,false),
                            LinePercentData("三等奖",120,false),
                            LinePercentData("四等奖",112,false),
                            LinePercentData("五等奖",80,false),
                            LinePercentData("六等奖",80,false)
                    )
            ,421,233,521,154,215,623
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )
                    ),
            "dlt" to TrendDetailItem(3520f,90f,60f,32f,
                    listOf(
                            LinePercentData("一等奖",122,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",70,false),
                            LinePercentData("四等奖",58,false),
                            LinePercentData("五等奖",50,false),
                            LinePercentData("六等奖",160,false)
                    ),23,141,426,645,385,621
                    , listOf(
                    AnnularModel(52, Color.parseColor("#44a4f7")),
                    AnnularModel(35, Color.parseColor("#ffc90e")),
                    AnnularModel(21, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )),
            "fc3d" to TrendDetailItem(620f,25f,20f,32f,
                    listOf(
                            LinePercentData("一等奖",12,false),
                            LinePercentData("二等奖",180,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",-20,false),
                            LinePercentData("六等奖",20,false)
                    ),56,21,423,32,221,212
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )),
            "bj11x5" to TrendDetailItem(241f,25f,10f,22f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",28,false),
                            LinePercentData("五等奖",60,false),
                            LinePercentData("六等奖",160,false)
                    ),153,254,231,876,234,214
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )),
            "qxc" to TrendDetailItem(150f,72.5f,30f,12f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",60,false),
                            LinePercentData("六等奖",160,false)
                    ),232,5,245,562,231,321
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )),
            "bjk3" to TrendDetailItem(106f,25f,10f,22f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",28,false),
                            LinePercentData("五等奖",60,false),
                            LinePercentData("六等奖",160,false)
                    ),634,32,41,632,23,253
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )),
            "pl3" to TrendDetailItem(430f,25f,20f,32f,
                    listOf(
                            LinePercentData("一等奖",12,false),
                            LinePercentData("二等奖",180,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",20,false),
                            LinePercentData("六等奖",20,false)
                    ),232,5,245,562,231,321
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            )),
            "pl5" to TrendDetailItem(180f,90f,60f,32f,
                    listOf(
                            LinePercentData("一等奖",122,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",70,false),
                            LinePercentData("四等奖",58,false),
                            LinePercentData("五等奖",50,false),
                            LinePercentData("六等奖",160,false)
                    ),32,45,25,122,531,623
                    , listOf(
                    AnnularModel(42, Color.parseColor("#44a4f7")),
                    AnnularModel(24, Color.parseColor("#ffc90e")),
                    AnnularModel(12, Color.parseColor("#c862d7")),
                    AnnularModel(23, Color.parseColor("#3d76ea")),
                    AnnularModel(51, Color.parseColor("#4fd9fe"))
            ))
            )
}
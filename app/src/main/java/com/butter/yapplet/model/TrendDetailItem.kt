package com.butter.yapplet.model

/**
 * Created by zgyi on 2018-01-23.
 */
data class TrendDetailItem(val budgetMoney: Float, val archPercent: Float, val occuRate: Float,
                           val forceasRate: Float, val linePercent: List<LinePercentData>,
                           val v1: Int, val v2: Int, val v3: Int, val v4: Int, val v5: Int, val v6: Int
                           , val dimens: List<AnnularModel>
)
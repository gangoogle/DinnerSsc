package com.butter.yapple.ui.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.butter.yapple.model.AnnularModel
import com.butter.yapple.utils.ComUtils


/**
 * Created by zgyi on 2017-12-06.
 */
class AnnularView : View {
    var strokeW = ComUtils.dip2px(context, 5f)
    val textColor = Color.parseColor("#666666")
    val textSize = ComUtils.dip2px(context, 18f)
    val duration = 1000
    var paint: Paint? = null
    val padding = ComUtils.dip2px(context, 5f)
    //起始角度
    var endAngle: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    //最大角度
    val maxAngle = 360f

    var sourceItems: List<AnnularModel> = listOf()
    var handleitems: List<HandlePercent> = listOf()
    var top3Percent: Int = 0

    constructor(context: Context?) : super(context)

    init {
        initConfig()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initConfig()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun initConfig() {
        paint = Paint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var tMeasuredWidth = measuredWidth
        var tMeasuredHeight = measuredHeight
        if (tMeasuredWidth > tMeasuredHeight) {
            tMeasuredWidth = tMeasuredHeight
        } else {
            tMeasuredHeight = tMeasuredWidth
        }
        setMeasuredDimension(tMeasuredWidth, tMeasuredHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint?.isAntiAlias = true
        paint?.strokeWidth = strokeW.toFloat()
        paint?.style = Paint.Style.STROKE
        paint?.color = Color.BLUE
        val rectF = RectF(padding.toFloat(), padding.toFloat(), width.toFloat() - padding,
                height.toFloat() - padding)
        val angleUnit = 360f / 100f
        for (index in handleitems.indices) {
            val handleItem = this.handleitems[index]
            paint?.color = handleItem.color
            val startA = handleItem.start * angleUnit
            val maxA = handleItem.start * angleUnit + handleItem.end * angleUnit
            if (endAngle >= startA && endAngle <= maxA) {
                canvas?.drawArc(rectF, startA, endAngle.toFloat() - startA, false, paint)
            } else if (endAngle >= maxA) {
                canvas?.drawArc(rectF, startA, maxA - startA, false, paint)
            }
        }
        if (this.handleitems.size > 0) {
            //绘制top3文本
            paint?.color = textColor
            paint?.textSize = width / 5f
            paint?.style = Paint.Style.FILL
            var text = ""
            if (top3Percent * (360 / 100) > endAngle) {
                text = "${endAngle / (360 / 100)}%"
            } else {
                text = "${top3Percent}%"
            }
            val rect = Rect()
            paint?.getTextBounds(text, 0, text.length, rect)
            canvas?.drawText(text, width / 2f - rect.width() / 2f, height / 2f + rect.height() / 2f, paint)
        }

    }

    /**
     * 设置线条宽度
     */
    fun setStrokeWidth(strokeWidth: Int) {
        this.strokeW = strokeWidth
    }

    fun setData(items: List<AnnularModel>, top3Percent: Int) {
        this.sourceItems = items
        //处理数据 累加percent
        val thandleItems = mutableListOf<HandlePercent>()
        var start = 0
        for (value in items) {
            val handlePercent = HandlePercent(start, value.percent, value.color)
            start = start + value.percent
            thandleItems.add(handlePercent)
        }
        this.handleitems = thandleItems
        this.top3Percent = top3Percent
        val objAni = ObjectAnimator.ofInt(this, "endAngle", 0, 360)
        objAni.setDuration(duration.toLong())
        objAni.start()
    }

    data class HandlePercent(val start: Int, val end: Int, val color: Int)
}
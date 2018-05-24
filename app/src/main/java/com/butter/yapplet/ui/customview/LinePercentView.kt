package com.butter.yapplet.ui.customview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.butter.yapplet.utils.ComUtils


/**
 * Created by zgyi on 2017-11-29.
 * 线形百分比控件
 */
class LinePercentView : View {
    val lineHeight = ComUtils.dip2px(context, 3f)
    val textSize = ComUtils.dip2px(context, 12f)
    val margin = ComUtils.dip2px(context, 15f)
    var paint: Paint? = null
    val lineBlueColor = Color.parseColor("#689af4")
    val lineYellowColor = Color.parseColor("#ffc56d")
    val backGroundColor = Color.parseColor("#dde2e9")
    val lineRedColor = Color.RED
    val padding = ComUtils.dip2px(context, 10f)
    var completePercent: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    var beyondPercent: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    var mHasData: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val tMeasureWidth = measuredWidth
        var tMeasureHeight = measuredHeight
        //计算文本的高度
        val paint = Paint()
        paint.textSize = textSize.toFloat()
        val rect = Rect()
        val text = "+0%"
        paint.getTextBounds("+0%", 0, text.length, rect)
        tMeasureHeight = margin * 2 + lineHeight
        setMeasuredDimension(tMeasureWidth, tMeasureHeight)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint = Paint()
        paint?.isAntiAlias = true
        paint?.strokeWidth = lineHeight.toFloat()
        paint?.strokeCap = Paint.Cap.ROUND//圆头
        //文本数据
        paint?.textSize = textSize.toFloat()
        val rect = Rect()
        //获取一些限制数据
        val maxWidth = width.toFloat() - padding
        val startWidth = padding.toFloat()
        val heightCenter = height / 2f
        //绘制底栏
        paint?.color = backGroundColor
        canvas?.drawLine(padding.toFloat(), heightCenter, maxWidth,
                heightCenter, paint)
        //绘制第一层
        if (mHasData && completePercent >=0) {
            paint?.color = lineBlueColor
            val completeLength = (maxWidth - startWidth) / 100 * completePercent
            canvas?.drawLine(startWidth, heightCenter, startWidth + completeLength,
                    heightCenter, paint)
            //蓝色文本
            if (beyondPercent == 0) {
                paint?.color = lineBlueColor
                //生成文字
                val text = "$completePercent%"
                //计算文字宽高
                paint?.getTextBounds(text, 0, text.length, rect)
                var textX = completeLength + startWidth - rect.width()
                var textY = heightCenter + margin
                //完成进度条位置小与文本长度
                if (completeLength - rect.width() < 0) {
                    textX = startWidth
                }
                canvas?.drawText(text, textX, textY, paint)
            }

        }
        //绘制第二层
        if (mHasData && beyondPercent > 0) {
            paint?.color = lineYellowColor
            var beyondLength = maxWidth
            //控制范围
            if (beyondPercent <= 100) {
                beyondLength = (maxWidth - startWidth) - ((maxWidth - startWidth) / 100 * beyondPercent)
            } else {
                beyondLength = 0f
            }
            canvas?.drawLine(maxWidth, heightCenter, beyondLength + startWidth,
                    heightCenter, paint)
            //绘制黄色文本
            paint?.color = lineYellowColor
            val text = "+$beyondPercent%"
            paint?.getTextBounds(text, 0, text.length, rect)
            var textX = beyondLength + startWidth
            var textY = heightCenter + margin
            if (startWidth + beyondLength + rect.width() > maxWidth) {
                textX = maxWidth - rect.width()
            }
            canvas?.drawText(text, textX, textY, paint)

        }
        //绘制红色
        if (mHasData && completePercent < 0) {
            paint?.color = lineRedColor
            var tempMinusPercent = -completePercent
            if (tempMinusPercent > 100) {
                tempMinusPercent = 100
            }
            var minusLength = (maxWidth - startWidth) / 100 * tempMinusPercent
            canvas?.drawLine(startWidth, heightCenter, startWidth + minusLength,
                    heightCenter, paint)
            //红色文本
            paint?.color = lineRedColor
            //生成文字
            val text = "${completePercent}%"
            //计算文字宽高
            paint?.getTextBounds(text, 0, text.length, rect)
            var textX = minusLength + startWidth - rect.width()
            var textY = heightCenter + margin
            //完成进度条位置小与文本长度
            if (minusLength - rect.width() < 0) {
                textX = startWidth
            }
            canvas?.drawText(text, textX, textY, paint)
        }


    }

    fun setPercent(percent: Int, hasData: Boolean) {
        //清空页面值
        completePercent = 0
        beyondPercent = 0
        mHasData = hasData

        var tempCompletePercent = percent
        var tempBeyondPercent = 0
        if (percent > 100) {
            tempCompletePercent = 100
            tempBeyondPercent = percent - 100
        }

        val objCompleteAnim = ObjectAnimator.ofInt(this, "completePercent",
                0, tempCompletePercent).setDuration(800)
        objCompleteAnim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                stratBeyondAnim(tempBeyondPercent)

            }
        })
        objCompleteAnim.setInterpolator(AccelerateDecelerateInterpolator())
        objCompleteAnim.start()
    }

    /**
     * 执行超出的百分比动画
     */
    fun stratBeyondAnim(percent: Int) {
        val objBeyondPercent = ObjectAnimator.ofInt(this, "beyondPercent",
                0, percent).setDuration(800)
        objBeyondPercent.setInterpolator(AccelerateDecelerateInterpolator())
        objBeyondPercent.start()
    }
}
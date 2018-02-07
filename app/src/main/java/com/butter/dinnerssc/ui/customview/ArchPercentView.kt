package com.butter.dinnerssc.ui.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.butter.dinnerssc.R
import com.butter.dinnerssc.utils.ComUtils

/**
 * Created by zgyi on 2017-11-27.
 */
class ArchPercentView : View {
    var paint: Paint? = null
    val strokeWidth: Int = ComUtils.dip2px(context, 3f)
    val angle = 30f
    var maxAngle = angle * 2f + 180f
    var startAngle = -180 - angle
    var finishPercent: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomColor = resources.getColor(R.color.colorDeepBlue)
    var percentColor = resources.getColor(R.color.white)
    var textColor = resources.getColor(R.color.colorWhite2)
    var margin = 10f

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //始终保持是正方形
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
        paint = Paint()
        paint?.isAntiAlias = true
        paint?.strokeWidth = strokeWidth.toFloat()
        paint?.style = Paint.Style.STROKE
        //计算弧线区域
        val rectF = RectF(margin, margin, width - margin, width - margin)
        //绘制底部
        paint?.color = bottomColor
        paint?.strokeCap = Paint.Cap.ROUND
        canvas?.drawArc(rectF, startAngle, maxAngle, false, paint)
        //绘制完成度弧线
        paint?.color = percentColor
        //宽度比底大一点防止锯齿出现
        paint?.strokeWidth = strokeWidth.toFloat() + 1f
        var finishAngle = maxAngle / 100 * finishPercent
        if (finishAngle > maxAngle) {
            finishAngle = maxAngle
        }
        canvas?.drawArc(rectF, startAngle, finishAngle, false, paint)
        //绘制头部小圆点
        canvas?.save()
        paint?.style = Paint.Style.FILL
        canvas?.rotate(270f - angle + finishAngle, width / 2f, width / 2f)
        canvas?.drawCircle(width / 2f, margin, strokeWidth / 2f + 2f, paint)
        canvas?.restore()

        //绘制文本
        paint?.color = textColor
        paint?.textSize = width / 5f
        val text = "${finishPercent.toInt()}%"
        //获取字符串的宽高
        var rect = Rect()
        paint?.getTextBounds(text, 0, text.length, rect)
        val textHeight = rect.height()
        val textWidth = rect.width()
        canvas?.drawText(text, width / 2f - (textWidth?.div(2f)!!), width / 2f + (textHeight.div(2)), paint)
    }

    fun setPercent(newFinishPercent: Float) {
        val animator = ObjectAnimator.ofFloat(this, "finishPercent", newFinishPercent)
        animator.duration = 500L
        animator.start()
    }
}
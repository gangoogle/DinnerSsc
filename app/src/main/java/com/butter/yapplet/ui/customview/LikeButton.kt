package com.butter.yapplet.ui.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import com.butter.yapplet.R
import java.util.*

/**
 * Created by zgyi on 2017/10/13.
 */
class LikeButton : View {
    var paint = Paint()
    var centerX = 0f
    var centerY = 0f
    var isLike = false
    var likeAlpha = 0
        set(value) {
            field = value
            invalidate()
        }
    var notLikeAlpha = 255
        set(value) {
            field = value
            invalidate()
        }
    var likeScale = 1f
        set(value) {
            field = value
            invalidate()
        }
    var notLikeScale = 1f
        set(value) {
            field = value
            invalidate()
        }
    var shingingScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    var shingingAlpha = 0
        set(value) {
            field = value
            invalidate()
        }
    var circleAlpha = 0
        set(value) {
            field = value
            invalidate()
        }
    var circleScale = 1.0f
        set(value) {
            field = value
            invalidate()
        }
    var textUpAlpha = 255
        set(value) {
            field = value
            invalidate()
        }
    var textUpTransition = 0f
        set(value) {
            field = value
            invalidate()
        }
    var textDownAlpha = 0
        set(value) {
            field = value
            invalidate()
        }
    var textDownTransition = 0f
        set(value) {
            field = value
            invalidate()
        }
    val duration: Long = 500
    var likeNum: Int = 0
    var numInInfo: NumInfo = NumInfo()
    var numOutInfo: NumInfo = NumInfo()
    var numHeadInfo: NumInfo = NumInfo()

    constructor(context: Context) : super(context) {
        create()
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        create()
    }

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)

    fun create() {
        val t = Random().nextInt(100) + 1
        likeNum = t
    }

    fun addLike() {
        isLike = true
        likeAnim()
        addLikeNum()
    }

    fun notLike() {
        if (likeNum == 0) {
            return
        }
        isLike = false
        notLikeAnim()
        minusLikeNum()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //计算View的位置及长度
        initSize()
        paint.isAntiAlias = true
        //绘制屏幕中心线
//        canvas?.drawLine(centerX, 0f, centerX, height.toFloat(), paint)
//        canvas?.drawLine(0f, centerY, width.toFloat(), centerY, paint)
        //生成图片位子大小信息
        val notLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_unselected)
        val notLikeBitmapInfo = LikeBitmapInfo(notLikeBitmap)
        val shiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected_shining)
        val shiningBitmapInfo = ShiningBitmapInfo(shiningBitmap)
        val likeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_messages_like_selected)
        val likeBitmapInfo = LikeBitmapInfo(likeBitmap)
        //绘制未点赞按钮
        canvas?.save()
        paint.alpha = notLikeAlpha
        canvas?.scale(notLikeScale, notLikeScale, centerX - notLikeBitmapInfo.width + notLikeBitmapInfo.centerX, centerY)
        canvas?.drawBitmap(notLikeBitmapInfo.bitmap, null, Rect((centerX - notLikeBitmapInfo.width).toInt(),
                (centerY - notLikeBitmapInfo.centerY).toInt(), centerX.toInt(),
                (centerY + notLikeBitmapInfo.centerY).toInt()), paint)
        canvas?.restore()
        //绘制点赞按钮
        canvas?.save()
        paint.alpha = likeAlpha
        canvas?.scale(likeScale, likeScale, centerX - likeBitmapInfo.width + likeBitmapInfo.centerX, centerY)
        canvas?.drawBitmap(likeBitmapInfo.bitmap, null, Rect((centerX - likeBitmapInfo.width).toInt(),
                (centerY - likeBitmapInfo.centerY).toInt(), centerX.toInt(),
                (centerY + likeBitmapInfo.centerY).toInt()), paint)
        canvas?.restore()
        //绘制闪亮效果
        canvas?.save()
        paint.alpha = shingingAlpha
        canvas?.scale(shingingScale, shingingScale, centerX - notLikeBitmapInfo.width + shiningBitmapInfo.centerX,
                centerY - notLikeBitmapInfo.centerY - shiningBitmapInfo.centerY + shiningBitmapInfo.centerY)
        canvas?.drawBitmap(shiningBitmapInfo.bitmap, null, Rect((centerX - notLikeBitmapInfo.width).toInt(),
                (centerY - notLikeBitmapInfo.centerY - shiningBitmapInfo.centerY).toInt(),
                (centerX - notLikeBitmapInfo.width + shiningBitmapInfo.width).toInt(),
                (centerY - notLikeBitmapInfo.centerY - shiningBitmapInfo.centerY + shiningBitmapInfo.height).toInt()), paint)
        canvas?.restore()
        //绘制点赞按钮的底部圆圈
        canvas?.save()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.color = Color.parseColor("#ED7253")
        paint.alpha = circleAlpha
        canvas?.scale(circleScale, circleScale, centerX - notLikeBitmapInfo.width + notLikeBitmapInfo.centerX, centerY)
        canvas?.drawCircle(centerX - notLikeBitmapInfo.width + notLikeBitmapInfo.centerX, centerY, notLikeBitmapInfo.centerX, paint)
        canvas?.restore()
        //绘制点赞数
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 1f
        paint.textSize = 30f
        paint.color = Color.parseColor("#c5c3c5")
        val textHeight = -paint.getFontMetrics().ascent
        canvas?.save()
        canvas?.translate(textUpTransition / 10, textUpTransition)
        paint.alpha = textUpAlpha
        canvas?.drawText(numOutInfo.number, centerX + numOutInfo.offsetX, centerY + textHeight / 2, paint)
        canvas?.restore()
        canvas?.save()
        canvas?.translate(textDownTransition / 10, textDownTransition)
        paint.alpha = textDownAlpha
        canvas?.drawText(numInInfo.number, centerX + numInInfo.offsetX, centerY + textHeight / 2, paint)
        canvas?.restore()
        paint.alpha = 255
        canvas?.drawText(numHeadInfo.number, centerX, centerY + textHeight / 2, paint)

    }

    private fun initSize() {
        centerX = width / 2f
        centerY = height / 2f

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> onClick()
        }
        return true
    }

    private fun onClick() {
        if (isLike) {
            notLikeAnim()
            minusLikeNum()
        } else {

            likeAnim()
            addLikeNum()
        }
        isLike = !isLike
    }

    private fun minusLikeNum() {
        var headString: String = ""
        var outString: String = ""
        var inString: String = ""
        val oldNum = likeNum
        likeNum--
        val newNum = likeNum
        //转换成char数组
        var oldCharArray = oldNum.toString().toCharArray()
        val newCharArray = newNum.toString().toCharArray()
        if (newCharArray.size < oldCharArray.size) {
            headString = ""
            outString = oldNum.toString()
            inString = newNum.toString()
        } else {
            for ((index, newValue) in newCharArray.withIndex()) {
                val oldValue = oldCharArray[index]
                if (newValue.equals(oldValue)) {
                    headString = headString.plus(newValue)
                } else {
                    outString = outString.plus(oldValue)
                    inString = inString.plus(newValue)
                }
            }
        }
        var textWidths = FloatArray(2)
        paint.getTextWidths("12", textWidths);
        numHeadInfo = NumInfo(headString, 0f)
        numOutInfo = NumInfo(outString, textWidths[0] * headString.length)
        numInInfo = NumInfo(inString, textWidths[0] * headString.length)
    }

    private fun addLikeNum() {
        var headString: String = ""
        var outString: String = ""
        var inString: String = ""
        //得出减去的位数
        val oldNum = likeNum
        likeNum++
        val newNum = likeNum
        //转换成char数组
        var oldCharArray = oldNum.toString().toCharArray()
        val newCharArray = newNum.toString().toCharArray()
        //可能是进位的状态，这种状态就要全部替换，每天保留
        if (newCharArray.size > oldCharArray.size) {
            headString = ""
            outString = oldNum.toString()
            inString = newNum.toString()
        } else {
            //获取每个字符的宽度
            //尾部需要做动画，需要保留头部
            for ((index, newValue) in newCharArray.withIndex()) {
                val oldValue = oldCharArray[index]
                if (newValue.equals(oldValue)) {
                    headString = headString.plus(newValue)
                } else {
                    outString = outString.plus(oldValue)
                    inString = inString.plus(newValue)
                }
            }
        }
        var textWidths = FloatArray(2)
        paint.getTextWidths("12", textWidths);
        numHeadInfo = NumInfo(headString, 0f)
        numOutInfo = NumInfo(outString, textWidths[0] * headString.length)
        numInInfo = NumInfo(inString, textWidths[0] * headString.length)
    }

    private fun likeAnim() {
        //scale
        val likeScaleObj = ObjectAnimator.ofFloat(this, "likeScale", 0f, 1f).setDuration(duration)
        likeScaleObj.setInterpolator(AnticipateOvershootInterpolator())
        val notLikeScaleObj = ObjectAnimator.ofFloat(this, "notLikeScale", 1f, 0.6f).setDuration(300)
        val shingingScaleObj = ObjectAnimator.ofFloat(this, "shingingScale", 0f, 1f).setDuration(duration)
        shingingScaleObj.setInterpolator(AnticipateOvershootInterpolator())
        val circleScaleObj = ObjectAnimator.ofFloat(this, "circleScale", 0.5f, 1.1f).setDuration(duration)
        circleScaleObj.setInterpolator(AnticipateOvershootInterpolator())
        //alpha
        val likeAlphaObj = ObjectAnimator.ofInt(this, "likeAlpha", 0, 255).setDuration(duration)
        val notLikeAlphaObj = ObjectAnimator.ofInt(this, "notLikeAlpha", 255, 0).setDuration(duration)
        val shingingAlphaObj = ObjectAnimator.ofInt(this, "shingingAlpha", 0, 255).setDuration(duration)
        val circleAlphaObj = ObjectAnimator.ofInt(this, "circleAlpha", 0, 150, 0).setDuration(duration)
        val animSet = AnimatorSet()
        //文本
        val outAlphaObj = ObjectAnimator.ofInt(this, "textUpAlpha", 255, 0).setDuration(duration)
        val outTransitionObj = ObjectAnimator.ofFloat(this, "textUpTransition", 0f, -50f).setDuration(duration)
        val inAlphaObj = ObjectAnimator.ofInt(this, "textDownAlpha", 0, 255).setDuration(duration)
        val inTransitionObj = ObjectAnimator.ofFloat(this, "textDownTransition", 50f, 0f).setDuration(duration)
        animSet.playTogether(likeAlphaObj, notLikeAlphaObj, likeScaleObj, shingingAlphaObj,
                shingingScaleObj, notLikeScaleObj, circleScaleObj, circleAlphaObj, outAlphaObj, outTransitionObj,
                inAlphaObj, inTransitionObj)
        animSet.start()

    }

    private fun notLikeAnim() {
        //scale
        val likeScaleObj = ObjectAnimator.ofFloat(this, "likeScale", 1f, 0.6f).setDuration(duration)
        val notLikeScaleObj = ObjectAnimator.ofFloat(this, "notLikeScale", 0.8f, 1f).setDuration(duration)
        val shingingScaleObj = ObjectAnimator.ofFloat(this, "shingingScale", 1f, 0f).setDuration(duration)
        //alpha
        val likeAlphaObj = ObjectAnimator.ofInt(this, "likeAlpha", 255, 0).setDuration(duration)
        val notLikeAlphaObj = ObjectAnimator.ofInt(this, "notLikeAlpha", 0, 255).setDuration(duration)
        val shingingAlphaObj = ObjectAnimator.ofInt(this, "shingingAlpha", 255, 0).setDuration(duration)
        //文本
        val outAlphaObj = ObjectAnimator.ofInt(this, "textUpAlpha", 255, 0).setDuration(duration)
        val outTransitionObj = ObjectAnimator.ofFloat(this, "textUpTransition", 0f, 50f).setDuration(duration)
        val inAlphaObj = ObjectAnimator.ofInt(this, "textDownAlpha", 0, 255).setDuration(duration)
        val inTransitionObj = ObjectAnimator.ofFloat(this, "textDownTransition", -50f, 0f).setDuration(duration)
        val animSet = AnimatorSet()
        animSet.playTogether(likeAlphaObj, notLikeAlphaObj, likeScaleObj, notLikeScaleObj, shingingAlphaObj,
                shingingScaleObj, outAlphaObj, outTransitionObj, inAlphaObj, inTransitionObj)
        animSet.start()
    }

    data class LikeBitmapInfo(val bitmap: Bitmap, val width: Float = 50f, val height: Float = 50f,
                              val centerX: Float = 25f, val centerY: Float = 25f)

    data class ShiningBitmapInfo(val bitmap: Bitmap, val width: Float = 50f, val height: Float = 50f,
                                 val centerX: Float = 25f, val centerY: Float = 25f)

    data class NumInfo(val number: String = "", val offsetX: Float = 0f)
}
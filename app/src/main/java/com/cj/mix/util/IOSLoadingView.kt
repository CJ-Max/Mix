package com.cj.mix.util

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.AttrRes
import com.cj.mix.R

/**
 * Created by Oliver on 2022/8/18.
 */
class IOSLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private val DEFAULT_HEIGHT = 22f
        private val DEFAULT_PETAL_COLOR: Int = Color.WHITE
        private val DEFAULT_PETAL_LENGTH = ViewUtil.dp2px(3.83f)
        private val DEFAULT_PETAL_WIDTH = ViewUtil.dp2px(2.77f)
        private const val DEFAULT_PETAL_COUNT = 8
    }

    var petalColor: Int = DEFAULT_PETAL_COLOR      //颜色
    var petalLength: Float = DEFAULT_PETAL_LENGTH  //菊花瓣长度
    var petalWidth: Float = DEFAULT_PETAL_WIDTH    //菊花瓣宽度
    var petalCount: Int = DEFAULT_PETAL_COUNT      //个数

    private lateinit var mPaint: Paint
    private lateinit var mAnimator: ValueAnimator
    private var mCenterX: Float = 0f
    private var mCenterY: Float = 0f
    private var mCurrentIndex = 1

    init {
        attrs?.let {
            parseAttribute(getContext(), it)
        }
        initPaint()
    }

    //获取布局属性并设置属性默认值
    private fun parseAttribute(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.IOSLoadingView)
        petalColor = ta.getColor(R.styleable.IOSLoadingView_petalColor, DEFAULT_PETAL_COLOR)
        petalLength = ta.getDimension(R.styleable.IOSLoadingView_petalLength, DEFAULT_PETAL_LENGTH)
        petalWidth = ta.getDimension(R.styleable.IOSLoadingView_petalWidth, DEFAULT_PETAL_WIDTH)
        petalCount = ta.getInteger(R.styleable.IOSLoadingView_petalCount, DEFAULT_PETAL_COUNT)
        ta.recycle()
    }

    //初始化画笔
    private fun initPaint() {
        mPaint = Paint()
        with(mPaint) {
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
            color = petalColor
            strokeWidth = petalWidth
        }
    }

    //重写onMeasure支持wrap_content
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec))
    }

    private fun measure(measureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = ViewUtil.dp2px(DEFAULT_HEIGHT).toInt()
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = measuredWidth / 2f
        mCenterY = measuredHeight / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            for (i in 0 until petalCount) {
                mPaint.alpha = (i + 1 + mCurrentIndex) % petalCount * 255 / petalCount
                it.drawLine(
                    mCenterX,
                    petalWidth / 2f + 1,
                    mCenterX,
                    petalLength + petalWidth / 2f + 1,
                    mPaint
                )
                it.rotate(360f / petalCount, mCenterX, mCenterY)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mAnimator = ValueAnimator.ofInt(petalCount, 1)
        with(mAnimator) {
            duration = 1000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                mCurrentIndex = animation.animatedValue as Int
                invalidate()
            }
            start()
        }
    }

    override fun onDetachedFromWindow() {
        mAnimator.cancel()
        super.onDetachedFromWindow()
    }

}
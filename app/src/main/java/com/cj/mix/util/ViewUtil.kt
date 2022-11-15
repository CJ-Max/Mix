package com.cj.mix.util

/**
 * Created by Oliver on 2022/8/18.
 */
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.*
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

/**
 * Created by Jacksgong on 5/27/15.
 */
object ViewUtil {

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun getRootLeft(myView: View?): Int {
        if (myView == null) {
            return 0
        }
        return getWindowLocation(myView)[0] - getWindowLocation(myView.rootView)[0]
    }

    fun getRootTop(myView: View?): Int {
        if (myView == null) {
            return 0
        }
        return getWindowLocation(myView)[1] - getWindowLocation(myView.rootView)[1]
    }

    fun getScreenLocation(myView: View): IntArray {
        val location = IntArray(2)
        myView.getLocationOnScreen(location)
        return location
    }

    fun getWindowLocation(myView: View): IntArray {
        val location = IntArray(2)
        myView.getLocationInWindow(location)
        return location
    }

    fun adapterAboveNavigation(v: View?) {
        if (v == null) {
            return
        }

        if (!hasNavigationBar(v.context)) {
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            val navigationBarHeight = getNavigationBarHeight(v.resources)
            if (navigationBarHeight > 0) {
                val layoutParams = v.layoutParams as ViewGroup.MarginLayoutParams
                /*
                v.setPadding(
                        v.getPaddingLeft(),
                        v.getPaddingTop(),
                        v.getPaddingRight(),
                        v.getPaddingBottom() + navigationBarHeight
                );
                */
                layoutParams.bottomMargin = layoutParams.bottomMargin + navigationBarHeight
                v.requestLayout()
            }
        }
    }

    fun hasNavigationBar(context: Context): Boolean {
        val hasSoftwareKeys: Boolean

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && context is Activity) {

            val d = context.windowManager.defaultDisplay

            val realDisplayMetrics = DisplayMetrics()
            d.getRealMetrics(realDisplayMetrics)

            val realHeight = realDisplayMetrics.heightPixels
            val realWidth = realDisplayMetrics.widthPixels

            val displayMetrics = DisplayMetrics()
            d.getMetrics(displayMetrics)

            val displayHeight = displayMetrics.heightPixels
            val displayWidth = displayMetrics.widthPixels

            hasSoftwareKeys = realWidth - displayWidth > 0 || realHeight - displayHeight > 0
        } else {
            // just consider that has the permanent back menu, so do not need navigation bar.
            val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
            val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            hasSoftwareKeys = !hasMenuKey && !hasBackKey
        }
        return hasSoftwareKeys
    }

    fun getNavigationBarHeight(res: Resources): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            var navigationBarHeight = 0
            val navigationBarHeightId =
                res.getIdentifier("navigation_bar_height", "dimen", "android")
            if (navigationBarHeightId > 0) {
                navigationBarHeight = res.getDimensionPixelSize(navigationBarHeightId)
            }

            return navigationBarHeight
        }

        return 0
    }

    fun adapterBelowStatus(v: View?) {
        if (v == null) {
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            var statusBarHeight = 0
            val statusBarHeightId =
                v.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (statusBarHeightId > 0) {
                statusBarHeight = v.resources.getDimensionPixelSize(statusBarHeightId)
            }

            if (statusBarHeightId > 0) {
                val layoutParams = v.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.topMargin = layoutParams.topMargin + statusBarHeight
                v.requestLayout()
                /*
                 v.setPadding(
                         v.getPaddingLeft(),
                         v.getPaddingTop() + statusBarHeight,
                         v.getPaddingRight(),
                         v.getPaddingBottom()
                 );
                 */
            }
        }
    }

    fun getSize(view: View?): IntArray? {
        if (view == null) {
            return null
        }

        val size = IntArray(2)

        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )

        size[0] = view.measuredWidth
        size[1] = view.measuredHeight

        return size
    }

    fun dp2px(context: Context, dpValue: Float): Int {
        return dp2pxExact(context, dpValue).toInt()
    }

    fun dp2px(dpValue: Float): Float {
        return Resources.getSystem().displayMetrics.density * dpValue + 0.5f
    }

    fun dp2pxExact(context: Context?, dpValue: Float): Float {
        if (context == null) {
            // may be context destroyed by activity, but still invoke by fragment
            return 0f
        }
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 处理Android bug,改背景 padding被置位
     *
     * @param v
     * @param resid
     */
    fun setBackgroundResource(v: View?, resid: Int) {
        if (v == null || resid < 0) {
            return
        }

        val left = v.paddingLeft
        val right = v.paddingRight
        val top = v.paddingTop
        val bottom = v.paddingBottom

        v.setBackgroundResource(resid)
        v.setPadding(left, top, right, bottom)
    }

    fun changeViewIndex(child: View, index: Int) {
        val viewGroup = child.parent as ViewGroup
        val size = viewGroup.childCount
        if (index >= 0 && index < size) {
            val lp = child.layoutParams
            viewGroup.removeView(child)
            viewGroup.addView(child, index, lp)
        }
    }

    fun createPressableColorStateList(@ColorInt color: Int): ColorStateList {
        return ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
            intArrayOf(ColorUtils.compositeColors(0x22000000, color), color)
        )
    }

    fun getBitmap(view: View, height: Int, width: Int): Bitmap? {
        val bitmap = try {
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        } catch (e: Throwable) {
            return null
        }
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun getViewBitmap(
        view: View?,
        bgColor: Int = Color.WHITE,
        fgColor: Int = Color.TRANSPARENT
    ): Bitmap? {
        if (view == null || view.measuredWidth == 0 || view.measuredHeight == 0) {
            return null
        }
        try {
            val source = Bitmap.createBitmap(
                view.measuredWidth,
                view.measuredHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(source)
            canvas.drawColor(bgColor)
            view.draw(canvas)
            canvas.drawColor(fgColor)
            return source
        } catch (e: Throwable) {
        }
        return null
    }

    /**
     * @param blurRadius Supported range 0 < radius <= 25
     */
    fun blur(context: Context, source: Bitmap?, blurRadius: Float, corners: Float = 0f): Bitmap? {
        if (source == null) {
            return null
        }
        try {
            val outBmp = Bitmap.createBitmap(source.width, source.height, source.config)
            val rs = RenderScript.create(context)
            val input = Allocation.createFromBitmap(rs, source)
            val output = Allocation.createTyped(rs, input.type)
            val rsBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            rsBlur.setInput(input)
            rsBlur.setRadius(blurRadius)
            rsBlur.forEach(output)
            output.copyTo(outBmp)
            rs.destroy()
            if (corners > 0) {
                val cache = Bitmap.createBitmap(outBmp.width, outBmp.height, outBmp.config)
                val canvas = Canvas(cache)
                canvas.clipPath(Path().apply {
                    val bounds = RectF(0f, 0f, cache.width.toFloat(), cache.height.toFloat())
                    addRoundRect(bounds, corners, corners, Path.Direction.CW)
                })
                canvas.drawBitmap(outBmp, Matrix(), null)
                return cache
            }
            return outBmp
        } catch (e: Throwable) {
        }
        return null
    }
}

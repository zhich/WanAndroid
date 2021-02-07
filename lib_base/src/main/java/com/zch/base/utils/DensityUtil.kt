package com.zch.base.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build.VERSION
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager

/**
 * Created by zch on 2021-02-07.
 */
object DensityUtil {

    fun dp2px(c: Context, dpValue: Float): Int {
        val scale = c.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2sp(c: Context, dpValue: Float): Int {
        return TypedValue.applyDimension(1, dpValue, c.resources.displayMetrics).toInt()
    }

    fun px2dp(c: Context, pxValue: Float): Int {
        val scale = c.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun px2sp(c: Context, pxValue: Float): Int {
        val fontScale = c.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun sp2px(c: Context, spValue: Float): Int {
        val fontScale = c.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun sp2dp(c: Context, spValue: Float): Int {
        return TypedValue.applyDimension(2, spValue, c.resources.displayMetrics).toInt()
    }

    fun getScreenW(c: Context): Int {
        return c.resources.displayMetrics.widthPixels
    }

    fun getScreenH(c: Context): Int {
        return c.resources.displayMetrics.heightPixels
    }

    @TargetApi(17)
    fun getScreenRealH(context: Context): Int {
        val winMgr = context.getSystemService("window") as WindowManager
        val display = winMgr.defaultDisplay
        val dm = DisplayMetrics()
        val h: Int
        h = if (VERSION.SDK_INT >= 17) {
            display.getRealMetrics(dm)
            dm.heightPixels
        } else {
            try {
                val method = Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics::class.java)
                method.invoke(display, dm)
                dm.heightPixels
            } catch (var6: Exception) {
                display.getMetrics(dm)
                dm.heightPixels
            }
        }
        return h
    }

    fun getStatusBarH(context: Context): Int {
        var statusBarHeight = 0
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = field[obj].toString().toInt()
            statusBarHeight = context.resources.getDimensionPixelSize(x)
        } catch (var6: Exception) {
            var6.printStackTrace()
        }
        return statusBarHeight
    }

    fun getNavigationBarrH(c: Context): Int {
        val resources = c.resources
        val identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelOffset(identifier)
    }
}
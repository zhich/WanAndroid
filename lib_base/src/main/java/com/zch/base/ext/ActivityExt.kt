package com.zch.base.ext

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by zch on 2020-11-02.
 */
fun Activity.hideKeyboard() {
    if (!isKeyboardShowing()) {
        return
    }
    val v = window.peekDecorView()
    if (v != null && v.windowToken != null) {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun Activity.showKeyboard(edt: EditText) {
    if (!isKeyboardShowing()) {
        return
    }
    edt.requestFocus()
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(0, InputMethodManager.SHOW_FORCED)
}

private fun Activity.isKeyboardShowing(): Boolean {
    //获取当屏幕内容的高度
    val screenHeight = window.decorView.height
    //获取View可见区域的bottom
    val rect = Rect()
    //DecorView即为activity的顶级view
    window.decorView.getWindowVisibleDisplayFrame(rect)
    //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
    //选取screenHeight*2/3进行判断
    return screenHeight * 2 / 3 > rect.bottom
}
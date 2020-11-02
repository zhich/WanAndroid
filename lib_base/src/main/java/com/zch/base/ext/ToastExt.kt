package com.zch.base.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Created by zch on 2020-11-02.
 */
fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, content, duration).apply {
        show()
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun Context.longToast(content: String) {
    toast(content, Toast.LENGTH_LONG)
}

fun Context.longToast(@StringRes id: Int) {
    toast(id, Toast.LENGTH_LONG)
}

fun Fragment.toast(content: String) {
    activity?.toast(content)
}

fun Fragment.toast(@StringRes id: Int) {
    activity?.toast(id)
}

fun Fragment.longToast(content: String) {
    activity?.longToast(content)
}

fun Fragment.longToast(@StringRes id: Int) {
    activity?.longToast(id)
}
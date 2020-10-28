package com.zch.base.utils

import android.widget.Toast
import com.zch.base.BaseApp

/**
 * Created by zch on 2019/02/20.
 */
object ToastUtil {

    fun showShortText(text: String) {
        Toast.makeText(BaseApp.instance, text, Toast.LENGTH_SHORT).show()
    }

    fun showLongText(text: String) {
        Toast.makeText(BaseApp.instance, text, Toast.LENGTH_LONG).show()
    }
}
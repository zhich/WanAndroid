package com.zch.base.ext

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.zch.base.ext.listener.textWatcher

/**
 * Created by zch on 2020-11-02.
 */
fun EditText.handleInputContent(ivClearContent: ImageView) {
    ivClearContent.let { it ->
        textWatcher {
            onTextChanged { s, _, _, _ ->
                if (!s.isNullOrBlank() && hasFocus()) {
                    it.visibility = View.VISIBLE
                } else {
                    it.visibility = View.GONE
                }
            }
        }
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (!text.isNullOrBlank()) {
                    it.visibility = View.VISIBLE
                } else {
                    it.visibility = View.GONE
                }
            } else {
                it.visibility = View.GONE
            }
        }
        it.setOnClickListener {
            setText("")
            it.visibility = View.GONE
        }
    }
}
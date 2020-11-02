package com.zch.base.binding

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 * Created by zch on 2020-11-02.
 */
@BindingAdapter(value = ["afterTextChanged"])
fun EditText.afterTextChanged(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            action(s.toString())
        }
    })
}

@BindingAdapter(value = ["passwordVisible"])
fun EditText.passwordVisible(visible: Boolean) {
    inputType = if (visible) {
        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    } else {
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
    setSelection(length())
}
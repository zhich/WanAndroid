package com.zch.base.binding

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import com.zch.base.widget.edt.BaseEditorView

/**
 * Created by zch on 2020-10-21.
 */
@BindingAdapter(value = ["edtVal"])
fun BaseEditorView.edtVal(edtVal: String?) {
    setContent(edtVal)
}

@BindingAdapter(value = ["afterTextChanged"])
fun BaseEditorView.afterTextChanged(action: (String) -> Unit) {
    getEditText().addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            action(s.toString())
        }
    })
}
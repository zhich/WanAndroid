package com.zch.base.widget.edt

import android.content.Context
import androidx.annotation.CallSuper
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by zch on 2019/01/16.
 */
open class BaseEditorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    protected lateinit var tvTag: TextView
    protected lateinit var edtContent: EditText
    protected lateinit var ivClearContent: ImageView

    override fun onFinishInflate() {
        super.onFinishInflate()
        setListener()
    }

    @CallSuper
    protected open fun setListener() {
        setBaseListener()
    }

    private fun setBaseListener() {
        edtContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && this@BaseEditorView.edtContent.hasFocus()) {
                    this@BaseEditorView.ivClearContent.visibility = View.VISIBLE
                } else {
                    this@BaseEditorView.ivClearContent.visibility = View.GONE
                }
                this@BaseEditorView.OnTextChangedLitener?.invoke(this@BaseEditorView.edtContent.text.toString().trim())
            }

            override fun afterTextChanged(s: Editable) {}
        })
        edtContent.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (this@BaseEditorView.getContent().isNotEmpty()) {
                    this@BaseEditorView.ivClearContent.visibility = View.VISIBLE
                } else {
                    this@BaseEditorView.ivClearContent.visibility = View.GONE
                }
            } else {
                this@BaseEditorView.ivClearContent.visibility = View.GONE
            }
        }
        ivClearContent.setOnClickListener {
            this@BaseEditorView.edtContent.setText("")
            this@BaseEditorView.ivClearContent.visibility = View.GONE
        }
    }

    fun getContent(): String {
        return this.edtContent.text.toString().trim()
    }

    fun setContent(value: String?): BaseEditorView {
        this.edtContent.setText(value ?: "")
        return this
    }

    fun setInputType(type: Int): BaseEditorView {
        this.edtContent.inputType = type
        return this
    }

    fun setMaxLen(maxLen: Int): BaseEditorView {
        this.edtContent.filters = arrayOf(InputFilter.LengthFilter(maxLen))
        return this
    }

    fun setEditable(editable: Boolean): BaseEditorView {
        this.edtContent.isFocusable = editable
        this.edtContent.isFocusableInTouchMode = editable
        if (editable) {
            this.edtContent.requestFocus()
        }
        return this
    }

    fun getEditText(): EditText {
        return this.edtContent
    }

    fun getLeftTextView(): TextView {
        return this.tvTag
    }

    fun setTagMinWidth(width: Int): BaseEditorView {
        this.tvTag.minWidth = width
        return this
    }

    var OnTextChangedLitener: ((String) -> Unit)? = null
}

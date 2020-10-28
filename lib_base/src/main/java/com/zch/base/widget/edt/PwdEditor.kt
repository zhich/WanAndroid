package com.zch.base.widget.edt

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.zch.base.R
import kotlinx.android.synthetic.main.view_account_editor.view.*

/**
 * Created by zch on 2019/01/16.
 */
class PwdEditor @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BaseEditorView(context, attrs, defStyleAttr) {

    var ivTagIcon: ImageView
    var ivPwdVisible: ImageView

    private var mIsPwdVisiable: Boolean = false

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_pwd_editor, this)
        ivClearContent = findViewById(R.id.ivClearContent)
        edtContent = findViewById(R.id.edtValue)

        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseEditor)

        val hint: String? = ta.getString(R.styleable.BaseEditor_hint)
        val iconResId: Int = ta.getResourceId(R.styleable.BaseEditor_iconTag, -1)

        edtContent.hint = hint
        ivTagIcon = findViewById(R.id.ivTagIcon)
        ivTagIcon.setImageResource(iconResId)

        ivPwdVisible = findViewById(R.id.ivPwdVisible)
    }

    override fun setListener() {
        super.setListener()
        ivPwdVisible.setOnClickListener {
            setPwdVisible(!mIsPwdVisiable)
        }
    }

    private fun setPwdVisible(visible: Boolean): PwdEditor {
        if (visible) {
            mIsPwdVisiable = true
            edtValue.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            mIsPwdVisiable = false
            edtValue.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        edtValue.setSelection(edtValue.length())
        return this
    }
}
package com.zch.base.widget.edt

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.zch.base.R

/**
 * Created by zch on 2019/01/16.
 */
class AccountEditor @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : BaseEditorView(context, attrs, defStyleAttr) {

    var ivTagIcon: ImageView
    var ivMore: ImageView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_account_editor, this)
        ivClearContent = findViewById(R.id.ivClearContent)
        edtContent = findViewById(R.id.edtValue)
        ivMore = findViewById(R.id.ivMore)
        ivTagIcon = findViewById(R.id.ivTagIcon)

        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseEditor)

        val hint: String = ta.getString(R.styleable.BaseEditor_hint)
        val iconResId: Int = ta.getResourceId(R.styleable.BaseEditor_iconTag, -1)

        ta.recycle()

        edtContent.hint = hint
        ivTagIcon.setImageResource(iconResId)
    }
}
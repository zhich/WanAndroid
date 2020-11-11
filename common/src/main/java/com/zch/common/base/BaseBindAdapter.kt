package com.zch.common.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zch.common.R

/**
 * Created by zch on 2020-11-11.
 */
open class BaseBindAdapter<T>(layoutResId: Int, val br: Int) : BaseQuickAdapter<T, BaseBindAdapter.BindViewHolder>(layoutResId) {

    override fun convert(helper: BindViewHolder, item: T) {
        helper.binding.run {
            setVariable(br, item)
            executePendingBindings()
        }
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        return binding.root.apply {
            setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        }
    }

    class BindViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ViewDataBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }
}
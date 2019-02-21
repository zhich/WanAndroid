package com.zch.wanandroid.user.login

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zch.base.db.User
import com.zch.wanandroid.R

/**
 * Created by zch on 2018/9/11.
 */
class HistoryAccountAdapter(userList: MutableList<User>) :
        BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_history_account, userList) {

    override fun convert(helper: BaseViewHolder, item: User) {
        helper.setText(R.id.tvAccount, item.account)
                .addOnClickListener(R.id.ivDelete)
    }
}
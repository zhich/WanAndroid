package com.zch.mine.module.mycollection

import com.alibaba.android.arouter.facade.annotation.Route
import com.zch.base.constant.ARouterPathConstant
import com.zch.common.base.BaseActivity
import com.zch.mine.R

/**
 * Created by zch on 2020-11-09.
 */
@Route(path = ARouterPathConstant.Mine.MY_COLLECTION_ACTIVITY)
class MyCollectionActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_my_collection

    override fun initView() {
    }

    override fun initData() {
    }
}
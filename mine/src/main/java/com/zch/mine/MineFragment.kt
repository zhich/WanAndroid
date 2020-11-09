package com.zch.mine

import android.content.res.Configuration
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.zch.base.constant.ARouterPathConstant
import com.zch.common.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by zch on 2020-11-09.
 */
@Route(path = ARouterPathConstant.Mine.MINE_FRAGMENT)
class MineFragment : BaseFragment() {

    override fun getLayoutResId() = R.layout.fragment_mine

    override fun initView() {
        fitsLayoutOverlap()
    }

    override fun initData() {
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // 旋转屏幕为什么要重新设置布局与状态栏重叠呢？因为旋转屏幕有可能使状态栏高度不一样，如果你是使用的静态方法修复的，所以要重新调用修复
        fitsLayoutOverlap()
    }

    private fun fitsLayoutOverlap() {
        ImmersionBar.setTitleBar(this, toolbar)
    }
}
package com.zch.hometabs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.zch.base.constant.ARouterPathConstant
import com.zch.common.base.BaseVMActivity
import com.zch.hometabs.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zch on 2020-11-04.
 */
@Route(path = ARouterPathConstant.HomeTabs.MAIN_ACTIVITY)
class MainActivity : BaseVMActivity() {

    private lateinit var mTabsIcon: IntArray
    private lateinit var mTabsTitle: Array<String>

    override fun startObserve() {

    }

    override fun initView() {
        setContentView(R.layout.activity_main)
        initTabs()
    }

    private fun initTabs() {
        resources.obtainTypedArray(R.array.tab_icons).apply {
            val len = length()
            mTabsIcon = IntArray(len)
            for (i in 0 until len) {
                mTabsIcon[i] = getResourceId(i, 0)
            }
        }.recycle()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                supportFragmentManager.beginTransaction().also {
                    hide(tab.position, it)
                }.commit()
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                supportFragmentManager.beginTransaction().also {
                    show(tab.position, it)
                }.commit()
            }
        })

        mTabsTitle = resources.getStringArray(R.array.tab_texts)
        mTabsIcon.forEachIndexed { i, item ->
            LayoutInflater.from(this).inflate(R.layout.navigation_tab_item, null)?.run {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                findViewById<TextView>(R.id.title).text = mTabsTitle[i]
                findViewById<ImageView>(R.id.icon).setImageResource(item)
                tabLayout.addTab(tabLayout.newTab().setCustomView(this), i == 0)
            }
        }
    }

    private fun show(position: Int, transaction: FragmentTransaction) {
        val tag = mTabsTitle[position]
        supportFragmentManager.findFragmentByTag(tag)?.let {
            transaction.show(it)
        } ?: transaction.add(R.id.contentLayout, generateItemByIndex(position), tag)
    }

    private fun generateItemByIndex(i: Int) = when (i) {
        0 -> ARouter.getInstance().build(ARouterPathConstant.HomeTabs.HOME_FRAGMENT).navigation() as HomeFragment
        1 -> ARouter.getInstance().build(ARouterPathConstant.HomeTabs.HOME_FRAGMENT).navigation() as HomeFragment
        2 -> ARouter.getInstance().build(ARouterPathConstant.HomeTabs.HOME_FRAGMENT).navigation() as HomeFragment
        else -> ARouter.getInstance().build(ARouterPathConstant.HomeTabs.HOME_FRAGMENT).navigation() as HomeFragment
    }

    private fun hide(position: Int, transaction: FragmentTransaction) {
        supportFragmentManager.findFragmentByTag(mTabsTitle[position])?.let {
            transaction.hide(it)
        }
    }

    override fun initData() {
    }
}
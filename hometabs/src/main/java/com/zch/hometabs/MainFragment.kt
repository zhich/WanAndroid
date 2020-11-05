package com.zch.hometabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.zch.base.constant.ARouterPathConstant
import com.zch.common.base.BaseFragment
import com.zch.hometabs.home.HomeFragment
import com.zch.hometabs.navigation.NavigationFragment
import com.zch.hometabs.project.LatestProjectFragment
import com.zch.hometabs.square.SquareFragment
import com.zch.hometabs.system.SystemFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by zch on 2020-11-05.
 */
@Route(path = ARouterPathConstant.HomeTabs.MAIN_FRAGMENT)
class MainFragment : BaseFragment() {

    private lateinit var mTitles: Array<String>
    private var mFragments: Array<Fragment>
    private val mHomeFragment by lazy { HomeFragment() }
    private val mSquareFragment by lazy { SquareFragment() }
    private val mLatestProjectFragment by lazy { LatestProjectFragment() }
    private val mSystemFragment by lazy { SystemFragment() }
    private val mNavigationFragment by lazy { NavigationFragment() }

    override fun getLayoutResId() = R.layout.fragment_main

    init {
        mFragments = arrayOf(mHomeFragment, mSquareFragment, mLatestProjectFragment, mSystemFragment, mNavigationFragment)
    }

    override fun initView() {
        mTitles = resources.getStringArray(R.array.main_top_tab_texts)
        viewPager?.let {
            it.offscreenPageLimit = 1
            it.adapter = object : FragmentStateAdapter(this) {
                override fun createFragment(position: Int) = mFragments[position]

                override fun getItemCount() = mTitles.size
            }
            TabLayoutMediator(tabLayout, it) { tab, position ->
                tab.text = mTitles[position]
            }.attach()
        }
    }

    override fun initData() {
    }
}
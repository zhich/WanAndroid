package com.zch.wanandroid

import android.content.res.TypedArray
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.zch.base.rxlifecycle.RxLifecycleActivity
import com.zch.wanandroid.home.HomeFragment
import com.zch.wanandroid.navigation.NavigationFragment
import com.zch.wanandroid.project.ProjectFragment
import com.zch.wanandroid.wechat.WechatFragment
import com.zch.wanandroid.knowledgehierarchy.KnowledgeHierarchyFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zch on 2019/01/04.
 */
class MainActivity : RxLifecycleActivity() {

    private lateinit var mTabsIcon: IntArray
    private lateinit var mTabsTitle: Array<String>

    private var tvNavUsername: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initTabs()
    }

    private fun initViews() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        drawerLayout.run {
            drawerLayout.run {
                val toggle = ActionBarDrawerToggle(
                        this@MainActivity,
                        this,
                        toolbar
                        , R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close)
                addDrawerListener(toggle)
                toggle.syncState()
            }
        }
        navView.run {
            setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_collect -> Toast.makeText(baseContext, "收藏", Toast.LENGTH_SHORT).show()
                    R.id.nav_logout -> Toast.makeText(baseContext, "退出登录", Toast.LENGTH_SHORT).show()
                }
                true
            }
//            setNavigationItemSelectedListener {
//                when (it.itemId) {
//                    R.id.nav_collect -> Toast.makeText(baseContext, "收藏", Toast.LENGTH_SHORT).show()
//                    R.id.nav_logout -> Toast.makeText(baseContext, "退出登录", Toast.LENGTH_SHORT).show()
//                }
//                true
//            }
            tvNavUsername = getHeaderView(0).findViewById(R.id.tvUsername)

            // todo navView
        }
    }

    private fun initTabs() {
        val ta: TypedArray = resources.obtainTypedArray(R.array.tab_icons)
        val len = ta.length()

        mTabsIcon = IntArray(len)
        for (i in 0 until len) {
            mTabsIcon[i] = ta.getResourceId(i, 0)
        }
        ta.recycle()

        mTabsTitle = resources.getStringArray(R.array.tab_texts)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val transaction = supportFragmentManager.beginTransaction()
                hide(tab.position, transaction)
                transaction.commit()
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val transaction = supportFragmentManager.beginTransaction()
                show(tab.position, transaction)
                transaction.commit()
            }
        })

        mTabsIcon.forEachIndexed { i, item ->
            val view = LayoutInflater.from(this).inflate(R.layout.navigation_tab_item, null)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            view.layoutParams = params
            val title = view.findViewById<View>(R.id.title) as TextView
            title.text = mTabsTitle[i]
            val icon = view.findViewById<View>(R.id.icon) as ImageView
            icon.setImageResource(item)
            tabLayout.addTab(tabLayout.newTab().setCustomView(view), i == 0)
        }
    }

    private fun show(position: Int, transaction: FragmentTransaction) {
        val tag = mTabsTitle[position]

        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            val fragment = generateItemByIndex(position)
            val tag = mTabsTitle[position]
            transaction.add(R.id.contentLayout, fragment, tag)
        } else {
            transaction.show(fragment)
        }
    }

    private fun generateItemByIndex(i: Int): Fragment {
        return when (i) {
            0 -> HomeFragment()
            1 -> KnowledgeHierarchyFragment()
            2 -> WechatFragment()
            3 -> NavigationFragment()
            else -> ProjectFragment()
        }
    }

    private fun hide(position: Int, transaction: FragmentTransaction) {
        val tag = mTabsTitle[position]
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.hide(fragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                Toast.makeText(baseContext, "搜索", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

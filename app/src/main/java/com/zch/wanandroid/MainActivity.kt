package com.zch.wanandroid

import androidx.lifecycle.Observer
import android.content.res.TypedArray
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.LiveDataEventManager
import com.zch.base.cache.LoginCache
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.ext.toast
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
@Route(path = ARouterPathConstant.Main.MAIN_ACTIVITY)
class MainActivity : RxLifecycleActivity() {

    private lateinit var mTabsIcon: IntArray
    private lateinit var mTabsTitle: Array<String>

    private var tvNavUsername: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initTabs()

        LiveDataEventManager.with(LiveDataEventManager.EVENT_LOGIN_SUCCESS).observe(this, Observer {
            if (it == true) {
                tvNavUsername?.text = LoginCache.getCurrentAccount()
                navView.menu.findItem(R.id.nav_logout).isVisible = true
            } else {
                tvNavUsername?.text = getString(R.string.login)
                navView.menu.findItem(R.id.nav_logout).isVisible = false
            }
        })
    }

    override fun onDestroy() {
        LiveDataEventManager.remove(LiveDataEventManager.EVENT_LOGIN_SUCCESS, this)
        super.onDestroy()
    }

    private fun initViews() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
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
        navView.run {
            setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_collect -> toast("收藏")
                    R.id.nav_todo -> toast("待办")
                    R.id.nav_settings -> toast("设置")
                    R.id.nav_logout -> {
                        AlertDialog.Builder(this@MainActivity)
                                .setTitle(getString(R.string.tip))
                                .setMessage(getString(R.string.sure_to_logout_hint))
                                .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                                    // todo logout
                                }
                                .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
                                .show()
                    }
                }
                true
            }
            tvNavUsername = getHeaderView(0).findViewById(R.id.tvUsername)
            menu.findItem(R.id.nav_logout).isVisible = LoginCache.isLogin()
            tvNavUsername?.run {
                text = if (LoginCache.isLogin()) LoginCache.getCurrentAccount() else getString(R.string.login)
                setOnClickListener {
                    if (!LoginCache.isLogin()) {
                        ARouter.getInstance().build(ARouterPathConstant.User.LOGIN_ACTIVITY).navigation()
                    }
                }
            }
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

    private fun show(position: Int, transaction: androidx.fragment.app.FragmentTransaction) {
        var tag = mTabsTitle[position]
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = generateItemByIndex(position)
            tag = mTabsTitle[position]
            transaction.add(R.id.contentLayout, fragment, tag)
        } else {
            transaction.show(fragment)
        }
    }

    private fun generateItemByIndex(i: Int): androidx.fragment.app.Fragment {
        return when (i) {
            0 -> HomeFragment()
            1 -> KnowledgeHierarchyFragment()
            2 -> WechatFragment()
            3 -> NavigationFragment()
            else -> ProjectFragment()
        }
    }

    private fun hide(position: Int, transaction: androidx.fragment.app.FragmentTransaction) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                toast("搜索")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

package com.zch.wanandroid.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import cn.bingoogolapple.bgabanner.BGABanner
import com.zch.base.rxlifecycle.RxLifecycleFragment
import com.zch.wanandroid.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by zch on 2019/01/07.
 */
class HomeFragment : RxLifecycleFragment() {

    private lateinit var bannerList: MutableList<Banner>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
    }

    private fun initBanner() {
        banner.run {
            setDelegate(BGABanner.Delegate<ImageView, String> { _, _, _, position ->
                if (bannerList.size == 0) {
                    return@Delegate
                }
                val item = bannerList[position]
                // todo 跳转
                Toast.makeText(context, "跳转", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
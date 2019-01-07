package com.zch.wanandroid.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zch.base.rxlifecycle.RxLifecycleFragment
import com.zch.wanandroid.R

/**
 * Created by zch on 2019/01/07.
 */
class HomeFragment : RxLifecycleFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
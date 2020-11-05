package com.zch.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by zch on 2020-11-05.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initData()
}
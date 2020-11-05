package com.zch.hometabs.home

import com.zch.common.base.BaseVMFragment
import com.zch.hometabs.R
import com.zch.hometabs.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-11-04.
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val mHomeViewModel by viewModel<HomeViewModel>()

    override fun startObserve() {

    }

    override fun initView() {
        mBinding.run {
            viewModel = mHomeViewModel
        }
    }

    override fun initData() {
    }
}
package com.zch.hometabs.home

import com.alibaba.android.arouter.facade.annotation.Route
import com.zch.base.constant.ARouterPathConstant
import com.zch.common.base.BaseVMFragment
import com.zch.hometabs.R
import com.zch.hometabs.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-11-04.
 */
@Route(path = ARouterPathConstant.HomeTabs.HOME_FRAGMENT)
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun startObserve() {

    }

    override fun initView() {
        binding.run {
            viewModel = homeViewModel
        }
    }

    override fun initData() {
    }
}
package com.zch.hometabs.home

import com.zch.common.base.BaseVMFragment
import com.zch.hometabs.R
import com.zch.hometabs.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-11-04.
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val mArticleViewModel by viewModel<ArticleViewModel>()

    override fun startObserve() {
        mArticleViewModel.run {
            bannerUiState.observe(viewLifecycleOwner, {
                it.showSuccess?.let {

                }
            })
        }
    }

    override fun initView() {
        mBinding.run {
            viewModel = mArticleViewModel
        }
    }

    override fun initData() {
        refresh()
    }

    private fun refresh() {
        mArticleViewModel.fetchBanner()
    }
}
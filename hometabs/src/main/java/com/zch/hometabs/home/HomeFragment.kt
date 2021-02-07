package com.zch.hometabs.home

import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.zch.base.BaseApp
import com.zch.base.ext.longToast
import com.zch.base.img.loader.ImageLoaderManager
import com.zch.base.img.loader.ImageLoaderOptions
import com.zch.base.widget.CustomDecoration
import com.zch.common.BannerBean
import com.zch.common.base.BaseVMFragment
import com.zch.hometabs.R
import com.zch.hometabs.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-11-04.
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val mArticleViewModel by viewModel<ArticleViewModel>()
    private val mHomeArticleAdapter by lazy { HomeArticleAdapter() }
    private val banner by lazy { Banner<BannerBean, BannerImageAdapter<BannerBean>>(activity) }

    // TODO
    override fun startObserve() {
        mArticleViewModel.run {
            bannerUiState.observe(viewLifecycleOwner, {
                it.data?.let {
                    setBanner(it)
                }
            })
            articleUiState.observe(viewLifecycleOwner, {
                it.data?.let { resp ->
                    mHomeArticleAdapter.run {
                        if (it.isRefresh == true) {
                            replaceData(resp.datas)
                        } else {
                            addData(resp.datas)
                        }
                        loadMoreComplete()
                    }
                }
                it.errMsg?.let { msg ->
                    longToast(msg)
                }
                if (it.showEnd == true) {
                    mHomeArticleAdapter.loadMoreEnd()
                }
            })
        }
    }

    override fun initView() {
        mBinding.run {
            viewModel = mArticleViewModel
            adapter = mHomeArticleAdapter
        }
        mHomeArticleAdapter.run {
            if (headerLayoutCount > 0) {
                removeAllHeaderView()
            }
            addHeaderView(banner)
        }
        banner.let {
            it.addBannerLifecycleObserver(this)
            it.setIndicator(CircleIndicator(activity))
        }
        rvArticleList?.addItemDecoration(CustomDecoration(BaseApp.instance))
    }

    private fun setBanner(bannerList: List<BannerBean>) {
        banner.adapter = object : BannerImageAdapter<BannerBean>(bannerList) {
            override fun onBindView(holder: BannerImageHolder, data: BannerBean, position: Int, size: Int) {
                ImageLoaderManager.instance.showImage(holder.imageView, data.imagePath, ImageLoaderOptions.Builder()
                        .placeHolder(R.drawable.shape_gray_default_bg)
                        .errorDrawable(R.drawable.shape_gray_default_bg)
                        .build())
            }
        }
    }

    override fun initData() {
        refresh()
    }

    private fun refresh() {
        mArticleViewModel.run {
            fetchBanner()
            fetchHomeArticles(true)
        }
    }

    override fun onStart() {
        super.onStart()
        banner.start()
    }

    override fun onStop() {
        banner.stop()
        super.onStop()
    }

    override fun onDestroy() {
        banner.destroy()
        super.onDestroy()
    }
}
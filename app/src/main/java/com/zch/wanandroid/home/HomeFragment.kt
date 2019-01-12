package com.zch.wanandroid.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import cn.bingoogolapple.bgabanner.BGABanner
import com.zch.base.img.loader.ImageLoaderManager
import com.zch.base.img.loader.ImageLoaderOptions
import com.zch.base.rxlifecycle.RxLifecycleFragment
import com.zch.base.widget.CustomDecoration
import com.zch.wanandroid.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by zch on 2019/01/07.
 */
class HomeFragment : RxLifecycleFragment(), HomeContract.View {

    private lateinit var homePresenter: HomeContract.Presenter

    private lateinit var articleAdapter: ArticleAdapter

    private var isRefresh = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleAdapter = ArticleAdapter(mutableListOf())
        rvArticleList.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = articleAdapter
            addItemDecoration(CustomDecoration(context))
        }

        swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            homePresenter.fetchArticles(0)
        }

        homePresenter = HomePresenter(this)
        homePresenter.fetchBanners()
        homePresenter.fetchArticles(0)
    }

    @SuppressLint("CheckResult")
    override fun onFetchBannersSuccess(bannerList: MutableList<Banner>?) {
        if (bannerList == null) {
            return
        }
        val imgPathList = mutableListOf<String>()
        val titleList = mutableListOf<String>()
        Observable.fromIterable(bannerList)
                .subscribe {
                    imgPathList.add(it.imagePath)
                    titleList.add(it.title)
                }
        banner.run {
            setAutoPlayAble(imgPathList.size > 1)
            setData(imgPathList, titleList)
            setAdapter { _, imageView, imgUrl, _ ->
                ImageLoaderManager.instance.showImage(imageView as ImageView, imgUrl as String, ImageLoaderOptions.Builder()
                        .placeHolder(R.drawable.shape_gray_default_bg)
                        .errorDrawable(R.drawable.shape_gray_default_bg)
                        .build())
            }

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

    override fun onFetchArticlesSuccess(articleResp: ArticleResp) {
        articleAdapter.replaceData(articleResp.datas)
        swipeRefreshLayout.isRefreshing = false
    }
}
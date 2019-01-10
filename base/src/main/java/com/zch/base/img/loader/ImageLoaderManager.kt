package com.zch.base.img.loader

import android.widget.ImageView

/**
 * Created by zch on 2019/01/04.
 */
class ImageLoaderManager private constructor() : ImageLoader {

    private var mImageLoader: ImageLoader? = null

    init {
        mImageLoader = GlideImageLoader()
    }

    companion object {

        val instance: ImageLoaderManager by lazy {
            ImageLoaderManager()
        }

    }

    override fun showImage(imageView: ImageView, url: String?, options: ImageLoaderOptions?) {
        mImageLoader?.showImage(imageView, url, options)
    }

    override fun showImage(imageView: ImageView, drawable: Int, options: ImageLoaderOptions?) {
        mImageLoader?.showImage(imageView, drawable, options)
    }

    override fun showRoundImage(imageView: ImageView, url: String?, options: ImageLoaderOptions?) {
        mImageLoader?.showRoundImage(imageView, url, options)
    }

    override fun showRoundCornerImage(imageView: ImageView, url: String?, options: ImageLoaderOptions?, radius: Int) {
        mImageLoader?.showRoundCornerImage(imageView, url, options, radius)
    }
}

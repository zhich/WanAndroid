package com.zch.base.img.loader

import android.widget.ImageView

/**
 * Created by zch on 2019/01/04.
 */
interface ImageLoader {

    fun showImage(imageView: ImageView, url: String?, options: ImageLoaderOptions)

    fun showImage(imageView: ImageView, drawable: Int, options: ImageLoaderOptions)

    fun showRoundImage(imageView: ImageView, url: String?, options: ImageLoaderOptions)

    fun showRoundCornerImage(imageView: ImageView, url: String?, options: ImageLoaderOptions, radius: Int)
}
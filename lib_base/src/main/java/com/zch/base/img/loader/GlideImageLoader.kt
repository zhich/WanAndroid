package com.zch.base.img.loader

import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created by zch on 2019/01/04.
 */
class GlideImageLoader : ImageLoader {

    override fun showImage(imageView: ImageView, url: String?, options: ImageLoaderOptions?) {
        Glide.with(imageView.context)
                .load(url)
                .apply(loadOptions(bitmapTransform(RoundedCornersTransformation(0, 0)), options))
                .into(imageView)
    }

    override fun showImage(imageView: ImageView, drawable: Int, options: ImageLoaderOptions?) {
        Glide.with(imageView.context)
                .load(drawable)
                .apply(loadOptions(bitmapTransform(CircleCrop()), options))
                .into(imageView)
    }

    override fun showRoundImage(imageView: ImageView, url: String?, options: ImageLoaderOptions?) {
        Glide.with(imageView.context)
                .load(url)
                .apply(loadOptions(bitmapTransform(CircleCrop()), options))
                .into(imageView)
    }

    override fun showRoundCornerImage(imageView: ImageView, url: String?, options: ImageLoaderOptions?, radius: Int) {
        Glide.with(imageView.context)
                .load(url)
                .apply(loadOptions(bitmapTransform(RoundedCornersTransformation(radius, 0)), options))
                .into(imageView)
    }

    private fun loadOptions(requestOptions: RequestOptions, options: ImageLoaderOptions?): RequestOptions {
        if (options == null) {
            return requestOptions
        }
        if (options.getPlaceHolder() != -1) {
            requestOptions.placeholder(options.getPlaceHolder())
        }
        if (options.getErrorDrawable() != -1) {
            requestOptions.error(options.getErrorDrawable())
        }
        if (options.isSkipMemoryCache()) {
            requestOptions.skipMemoryCache(options.isSkipMemoryCache())
        }
        if (options.getSize() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                requestOptions.override(options.getSize()!!.width, options.getSize()!!.height)
            }
        }
        return requestOptions
    }
}

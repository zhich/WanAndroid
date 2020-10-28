package com.zch.base.img.loader

import android.util.Size

/**
 * Created by zch on 2019/01/04.
 */
class ImageLoaderOptions(
        private var placeHolder: Int = -1, // 当没有成功加载的时候显示的图片
        private var size: Size? = null, // 重新设定容器宽高
        private var errorDrawable: Int = -1,  // 加载错误的时候显示的 drawable
        private var isSkipMemoryCache: Boolean = false // 是否跳过内存缓存
) {
    fun getPlaceHolder() = placeHolder
    fun getSize() = size
    fun getErrorDrawable() = errorDrawable
    fun isSkipMemoryCache() = isSkipMemoryCache

    class Builder {
        private var placeHolder = -1
        private var size: Size? = null
        private var errorDrawable = -1
        private var isSkipMemoryCache = false

        fun placeHolder(drawable: Int): Builder {
            this.placeHolder = drawable
            return this
        }

        fun size(size: Size): Builder {
            this.size = size
            return this
        }

        fun errorDrawable(errorDrawable: Int): Builder {
            this.errorDrawable = errorDrawable
            return this
        }

        fun isSkipMemoryCache(isSkipMemoryCache: Boolean): Builder {
            this.isSkipMemoryCache = isSkipMemoryCache
            return this
        }

        fun build(): ImageLoaderOptions {

            return ImageLoaderOptions(
                    this.placeHolder,
                    this.size,
                    this.errorDrawable,
                    this.isSkipMemoryCache
            )
        }
    }
}
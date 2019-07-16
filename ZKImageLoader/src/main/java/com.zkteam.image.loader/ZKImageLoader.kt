package com.zkteam.image.loader

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.zkteam.sdk.ZKBase

class ZKImageLoader {

    companion object {
        // 双重校验锁式（Double Check)
        val instance: ZKImageLoader by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ZKImageLoader()
        }
    }


    /**
     * 设置默认的占位图
     */
    private fun setDefaultInfo(glideReuqestBuilder: RequestBuilder<Drawable>): RequestBuilder<Drawable> {
        return glideReuqestBuilder
            .placeholder(R.drawable.ic_zk_image_loader_base_place_holder)
            .error(R.drawable.ic_zk_image_loader_base_error)
            .fallback(R.drawable.ic_zk_image_loader_base_fallback)
            .transition(withCrossFade())
    }

    /**
     * 使用 Context 加载图片，包含 Activity 和相关子类
     */
    fun load(context: Context?, imageView: ImageView, url: String) {
        var mContext = context

        if (mContext == null) {
            mContext = ZKBase.context()
        }

        load(mContext, url).into(imageView)
    }

    /**
     * 使用 Fragment 加载图片
     */
    fun load(fragment: Fragment?, imageView: ImageView, url: String) {
        load(fragment, url).load(url).into(imageView)
    }

    /**
     * 更方便自行控制加载图片的样式，例如保存 bitmap
     */
    fun load(context: Context?, url: String): RequestBuilder<Drawable> {
        var mContext = context

        if (mContext == null) {
            mContext = ZKBase.context()
        }

        return setDefaultInfo(Glide.with(mContext).load(url))
    }


    /**
     * 更方便自行控制加载图片的样式，例如保存 bitmap
     */
    fun load(fragment: Fragment?, url: String): RequestBuilder<Drawable> {
        val requestManager: RequestManager = if (fragment == null) {
            Glide.with(ZKBase.context())
        } else {
            Glide.with(fragment)
        }
        return setDefaultInfo(requestManager.load(url))
    }

    /**
     * Fragment 取消图片加载
     *
     * 尽管及时取消不必要的加载是很好的实践，但这并不是必须的操作。实际上，当 Glide.with() 中传入的 Activity 或 Fragment 实例销毁时，Glide 会自动取消加载并回收资源。
     */
    fun clear(fragment: Fragment, imageView: ImageView) {
        Glide.with(fragment).clear(imageView)
    }

    /**
     * Activity 取消图片加载
     *
     * 尽管及时取消不必要的加载是很好的实践，但这并不是必须的操作。实际上，当 Glide.with() 中传入的 Activity 或 Fragment 实例销毁时，Glide 会自动取消加载并回收资源。
     */
    fun clear(activity: Activity, imageView: ImageView) {
        Glide.with(activity).clear(imageView)
    }
}
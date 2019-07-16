package com.zkteam.image.loader.demo

import android.os.Bundle
import android.view.View
import com.zkteam.image.loader.ZKImageLoader
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.sdk.testdata.TestBeautyData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData(bundle: Bundle?) {
        ZKImageLoader.instance.load(this, iv, TestBeautyData.getTop10HotWomenData()[0].url)
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    override fun initViews(contentView: View) {
        //function
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}

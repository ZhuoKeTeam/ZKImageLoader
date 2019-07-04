package com.zkteam.image.loader.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zkteam.image.loader.ZKImageLoader
import com.zkteam.sdk.testdata.TestBeautyData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ZKImageLoader.instance.load(this, iv, TestBeautyData.getTop10HotWomenData()[0].url)
    }
}

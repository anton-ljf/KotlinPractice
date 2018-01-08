package com.kotlinpractice.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kotlinpractice.MyApplication

/**
 * Created by jifeng.liu on 18/1/5.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
        initListener()
    }

    private fun initListener(){

    }

    /**
     *
     */

    open val mRetryClickListener : View.OnClickListener = View.OnClickListener {
        start()
    }
    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(this)?.watch(this)
    }
}
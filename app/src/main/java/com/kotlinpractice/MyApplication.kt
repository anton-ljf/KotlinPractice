package com.kotlinpractice

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 * Created by jifeng.liu on 18/1/5.
 */
class MyApplication : Application() {
    private var refWatcher: RefWatcher? = null

    companion object {
        private var TAG = "MyAppliction"

        var context: Context by Delegates.notNull()
            private set
        fun getRefWatcher(context:Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }
}
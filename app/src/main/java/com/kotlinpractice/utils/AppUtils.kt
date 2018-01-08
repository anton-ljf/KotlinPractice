package com.kotlinpractice.utils

import android.content.Context
import android.content.pm.PackageManager

/**
 * Created by jifeng.liu on 18/1/5.
 */
class AppUtils {
    init {

    }

    companion object {
        fun getVerName(context: Context): String {
            var versionName = ""
            try {
                val packageName = context.packageName
                versionName = context.packageManager
                        .getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return versionName
        }
    }

}
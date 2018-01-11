package com.kotlinpractice.utils

import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jifeng.liu on 18/1/11.
 */
public class SchedulerUtils {
    companion object {
        fun <T> ioToMain(): ObservableTransformer<T, T> {
            return ObservableTransformer<T, T> { upstream: Observable<T> ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}
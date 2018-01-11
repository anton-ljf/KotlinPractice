package com.kotlinpractice.mvp.model

import com.kotlinpractice.mvp.model.bean.HomeBean
import com.kotlinpractice.net.RetrofitManager
import com.kotlinpractice.utils.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by jifeng.liu on 18/1/11.
 */
class HomeModel {
    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{

        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}
package com.kotlinpractice.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.kotlinpractice.mvp.contract.HomeContract
import com.kotlinpractice.mvp.model.HomeModel
import com.kotlinpractice.mvp.model.bean.HomeBean

/**
 * Created by jifeng.liu on 18/1/11.
 */
class HomePresenter : BasePresenter<HomeContract.View>(),HomeContract.Presenter {
    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    private val homeModel: HomeModel by lazy {
        HomeModel()
    }

    override fun requestHomeData(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
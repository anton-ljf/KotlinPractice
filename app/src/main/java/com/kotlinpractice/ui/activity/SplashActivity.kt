package com.kotlinpractice.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.kotlinpractice.MainActivity
import com.kotlinpractice.R
import com.kotlinpractice.base.BaseActivity
import com.kotlinpractice.showToast
import com.kotlinpractice.utils.AppUtils
import com.orhanobut.logger.Logger
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by jifeng.liu on 18/1/5.
 */
class SplashActivity :BaseActivity(){
    private var alphaAnimation: AlphaAnimation?=null

    override fun layoutId(): Int = R.layout.activity_splash


    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        tv_version_name.text = "v${AppUtils.getVerName(this)}"

        //渐变展示启动屏
        alphaAnimation= AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })
    }

    fun redirectTo() {
//        val intent = Intent(this, MainActivity::class.java)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun start() {
        checkPermission()
    }

    private fun checkPermission(){
        val permisson = ArrayList<PermissionItem>()
        permisson.add(PermissionItem(Manifest.permission.READ_PHONE_STATE,"手机状态",R.drawable.permission_ic_phone))
        permisson.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,"存储空间",R.drawable.permission_ic_storage))
        HiPermission.create(this)
                .title("亲爱的上帝")
                .msg("为了能够正常使用，请开启这些权限吧！")
                .permissions(permisson)
                .style(R.style.PermissionDefaultBlueStyle)
                .animStyle(R.style.PermissionAnimScale)
                .checkMutiPermission(object : PermissionCallback{
                    override fun onClose() {
                        Logger.i( "permission_onClose")
                        showToast("用户关闭了权限")
                    }

                    override fun onFinish() {
                        showToast("初始化完毕！")
                        layout_splash.startAnimation(alphaAnimation)
                    }

                    override fun onDeny(permission: String, position: Int) {
                        Logger.i("permission_onDeny")
                    }

                    override fun onGuarantee(permission: String, position: Int) {
                        Logger.i("permission_onGuarantee")
                        redirectTo()
                    }
                })

    }
}
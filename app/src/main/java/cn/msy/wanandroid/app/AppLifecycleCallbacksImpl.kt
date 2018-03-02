package cn.msy.wanandroid.app

import android.app.Application
import android.content.Context
import com.jess.arms.base.delegate.AppLifecycles

/**
 * APP生命周期回调
 * @author msy
 */
class AppLifecycleCallbacksImpl : AppLifecycles {
    override fun attachBaseContext(base: Context?) {
    }

    override fun onCreate(application: Application?) {
        //执行一些需要在APP启动的初始化工作
    }

    override fun onTerminate(application: Application?) {
    }
}
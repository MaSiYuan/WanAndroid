package cn.msy.wanandroid.app

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.jess.arms.base.delegate.AppLifecycles
import com.jess.arms.di.module.GlobalConfigModule
import com.jess.arms.http.GlobalHttpHandler
import com.jess.arms.integration.ConfigModule
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 全局配置
 * @author msy
 */
class GlobalConfiguration : ConfigModule {
    override fun injectFragmentLifecycle(context: Context?, lifecycles: MutableList<FragmentManager.FragmentLifecycleCallbacks>?) {
    }

    override fun applyOptions(context: Context?, builder: GlobalConfigModule.Builder?) {
        //配置动态url
        builder?.okhttpConfiguration { context, builder ->
            RetrofitUrlManager.getInstance().with(builder).build()
            RetrofitUrlManager.getInstance().setGlobalDomain("http://www.wanandroid.com")
        }

        //配置公共参数
        builder?.globalHttpHandler(object : GlobalHttpHandler {
            override fun onHttpRequestBefore(chain: Interceptor.Chain?, request: Request?): Request? {
                try {
                    //添加公共参数
                    val authorizedUrlBuilder: HttpUrl.Builder? = request?.url()
                            ?.newBuilder()
                            ?.scheme(request.url()?.scheme())
                            ?.host(request.url()?.host())
                            ?.addQueryParameter("", "")

                    val newRequest: Request? = request?.newBuilder()
                            ?.method(request.method(), request.body())
                            ?.url(authorizedUrlBuilder?.build())
                            ?.build()

                    return newRequest

                } catch (e: Exception) {
                    return null
                }
            }

            override fun onHttpResultResponse(httpResult: String?, chain: Interceptor.Chain?, response: Response?): Response? {
                return response
            }
        })
    }

    override fun injectAppLifecycle(context: Context?, lifecycles: MutableList<AppLifecycles>?) {
    }

    override fun injectActivityLifecycle(context: Context?, lifecycles: MutableList<Application.ActivityLifecycleCallbacks>?) {
    }
}
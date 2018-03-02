package cn.msy.wanandroid.mvp.model

import android.app.Application
import cn.msy.wanandroid.mvp.contract.HomeContract
import cn.msy.wanandroid.mvp.model.api.ApiHome
import cn.msy.wanandroid.mvp.model.resp.ArticleResp
import cn.msy.wanandroid.mvp.model.resp.BannerResp
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class HomeModel @Inject
constructor(repositoryManager: IRepositoryManager, private var mGson: Gson?, private var mApplication: Application?) : BaseModel(repositoryManager), HomeContract.Model {

    override fun getArticle(pageIndex: Int): Observable<ArticleResp> {
        return mRepositoryManager.obtainRetrofitService(ApiHome::class.java).getHomeArticle(pageIndex)
    }

    override fun getBanner(): Observable<BannerResp> {
        return mRepositoryManager.obtainRetrofitService(ApiHome::class.java).getHomeBanner()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }

}
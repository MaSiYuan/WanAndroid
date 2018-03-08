package cn.msy.wanandroid.mvp.model

import android.app.Application

import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope

import javax.inject.Inject

import cn.msy.wanandroid.mvp.contract.CategoryContentContract
import cn.msy.wanandroid.mvp.model.api.ApiCategory
import cn.msy.wanandroid.mvp.model.resp.ArticleResp
import io.reactivex.Observable


@ActivityScope
class CategoryContentModel @Inject
constructor(repositoryManager: IRepositoryManager, private var mGson: Gson?, private var mApplication: Application?) : BaseModel(repositoryManager), CategoryContentContract.Model {

    override fun getSubCategory(index: Int?, cid: Int?): Observable<ArticleResp> {
        return mRepositoryManager.obtainRetrofitService(ApiCategory::class.java).getSubCategory(index, cid)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }

}
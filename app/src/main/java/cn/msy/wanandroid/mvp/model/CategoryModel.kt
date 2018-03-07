package cn.msy.wanandroid.mvp.model

import android.app.Application

import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope

import javax.inject.Inject

import cn.msy.wanandroid.mvp.contract.CategoryContract
import cn.msy.wanandroid.mvp.model.api.ApiCategory
import cn.msy.wanandroid.mvp.model.resp.CategoryResp
import io.reactivex.Observable


@ActivityScope
class CategoryModel @Inject
constructor(repositoryManager: IRepositoryManager, private var mGson: Gson?, private var mApplication: Application?) : BaseModel(repositoryManager), CategoryContract.Model {
    override fun getCategory(): Observable<CategoryResp> {
        return mRepositoryManager.obtainRetrofitService(ApiCategory::class.java).getCategory()
    }


    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }

}
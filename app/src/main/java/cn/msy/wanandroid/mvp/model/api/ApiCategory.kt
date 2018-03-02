package cn.msy.wanandroid.mvp.model.api

import cn.msy.wanandroid.mvp.model.resp.CategoryResp
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * 知识体系API
 * @author msy
 */
interface ApiCategory {

    @GET("/tree/json")
    fun getCategory(): Observable<CategoryResp>
}
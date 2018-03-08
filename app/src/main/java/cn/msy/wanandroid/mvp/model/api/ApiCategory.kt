package cn.msy.wanandroid.mvp.model.api

import cn.msy.wanandroid.mvp.model.resp.ArticleResp
import cn.msy.wanandroid.mvp.model.resp.CategoryResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 知识体系API
 * @author msy
 */
interface ApiCategory {

    @GET("/tree/json")
    fun getCategory(): Observable<CategoryResp>

    @GET("/article/list/{index}/json")
    fun getSubCategory(@Path("index") index: Int?,
                       @Query("cid") cid: Int?): Observable<ArticleResp>
}
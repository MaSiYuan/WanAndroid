package cn.msy.wanandroid.mvp.model.api

import cn.msy.wanandroid.mvp.model.resp.ArticleResp
import cn.msy.wanandroid.mvp.model.resp.BannerResp
import cn.msy.wanandroid.mvp.model.resp.FriendResp
import cn.msy.wanandroid.mvp.model.resp.HotKey
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

/**
 * 首页API
 * @author msy
 */
interface ApiHome {

    @GET("/article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<ArticleResp>

    @GET("/banner/json")
    fun getHomeBanner(): Observable<BannerResp>

    @GET("/friend/json")
    fun getHomeFriend(): Observable<FriendResp>

    @GET("/hotkey/json")
    fun getHotKey(): Observable<HotKey>
}
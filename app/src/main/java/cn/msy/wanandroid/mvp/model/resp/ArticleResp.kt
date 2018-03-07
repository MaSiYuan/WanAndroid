package cn.msy.wanandroid.mvp.model.resp

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * 首页文章返回
 * @author msy
 */

data class ArticleResp(
        @SerializedName("data") val data: ArticleData? = ArticleData(),
        @SerializedName("errorCode") val errorCode: Int? = 0,
        @SerializedName("errorMsg") val errorMsg: String? = ""
)

data class ArticleData(
        @SerializedName("curPage") val curPage: Int? = 0,
        @SerializedName("datas") val datas: List<Article?>? = listOf(),
        @SerializedName("offset") val offset: Int? = 0,
        @SerializedName("over") val over: Boolean? = false,
        @SerializedName("pageCount") val pageCount: Int? = 0,
        @SerializedName("size") val size: Int? = 0,
        @SerializedName("total") val total: Int? = 0
)

data class Article(
        @SerializedName("apkLink") val apkLink: String? = "",
        @SerializedName("author") val author: String? = "",
        @SerializedName("chapterId") val chapterId: Int? = 0,
        @SerializedName("chapterName") val chapterName: String? = "",
        @SerializedName("collect") val collect: Boolean? = false,
        @SerializedName("courseId") val courseId: Int? = 0,
        @SerializedName("desc") val desc: String? = "",
        @SerializedName("envelopePic") val envelopePic: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("link") val link: String? = "",
        @SerializedName("niceDate") val niceDate: String? = "",
        @SerializedName("origin") val origin: String? = "",
        @SerializedName("projectLink") val projectLink: String? = "",
        @SerializedName("publishTime") val publishTime: Long? = 0,
        @SerializedName("title") val title: String? = "",
        @SerializedName("visible") val visible: Int? = 0,
        @SerializedName("zan") val zan: Int? = 0
)
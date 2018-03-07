package cn.msy.wanandroid.mvp.model.resp

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * 知识体系
 * @author msy
 */

data class CategoryResp(
        @SerializedName("data") val data: List<Category?>? = listOf(),
        @SerializedName("errorCode") val errorCode: Int? = 0,
        @SerializedName("errorMsg") val errorMsg: String? = ""
)

data class Category(
        @SerializedName("children") val children: List<SubCategory?>? = listOf(),
        @SerializedName("courseId") val courseId: Int? = 0,
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("name") val name: String? = "",
        @SerializedName("order") val order: Int? = 0,
        @SerializedName("parentChapterId") val parentChapterId: Int? = 0,
        @SerializedName("visible") val visible: Int? = 0
):Serializable

data class SubCategory(
        @SerializedName("children") val children: List<Any?>? = listOf(),
        @SerializedName("courseId") val courseId: Int? = 0,
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("name") val name: String? = "",
        @SerializedName("order") val order: Int? = 0,
        @SerializedName("parentChapterId") val parentChapterId: Int? = 0,
        @SerializedName("visible") val visible: Int? = 0
):Serializable
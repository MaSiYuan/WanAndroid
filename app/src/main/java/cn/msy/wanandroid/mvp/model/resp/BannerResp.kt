package cn.msy.wanandroid.mvp.model.resp

import com.google.gson.annotations.SerializedName


/**
 * 首页Banner返回
 * @author msy.
 */

data class BannerResp(
        @SerializedName("data") val data: List<Banner?>? = listOf(),
        @SerializedName("errorCode") val errorCode: Int? = 0,
        @SerializedName("errorMsg") val errorMsg: String? = ""
)

data class Banner(
        @SerializedName("desc") val desc: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("imagePath") val imagePath: String? = "",
        @SerializedName("isVisible") val isVisible: Int? = 0,
        @SerializedName("order") val order: Int? = 0,
        @SerializedName("title") val title: String? = "",
        @SerializedName("type") val type: Int? = 0,
        @SerializedName("url") val url: String? = ""
)
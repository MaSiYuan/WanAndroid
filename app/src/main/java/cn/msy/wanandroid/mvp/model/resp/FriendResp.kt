package cn.msy.wanandroid.mvp.model.resp

import com.google.gson.annotations.SerializedName


/**
 * 常用网站
 * @author msy
 */

data class FriendResp(
        @SerializedName("data") val data: List<Friend?>? = listOf(),
        @SerializedName("errorCode") val errorCode: Int? = 0,
        @SerializedName("errorMsg") val errorMsg: String? = ""
)

data class Friend(
        @SerializedName("icon") val icon: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("link") val link: String? = "",
        @SerializedName("name") val name: String? = "",
        @SerializedName("order") val order: Int? = 0,
        @SerializedName("visible") val visible: Int? = 0
)
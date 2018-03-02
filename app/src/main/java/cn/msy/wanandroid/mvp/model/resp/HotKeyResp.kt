package cn.msy.wanandroid.mvp.model.resp
import com.google.gson.annotations.SerializedName


/**
 * 热词返回
 * @author msy
 */

data class HotKeyResp(
		@SerializedName("data") val data: List<HotKey?>? = listOf(),
		@SerializedName("errorCode") val errorCode: Int? = 0,
		@SerializedName("errorMsg") val errorMsg: String? = ""
)

data class HotKey(
		@SerializedName("id") val id: Int? = 0,
		@SerializedName("link") val link: String? = "",
		@SerializedName("name") val name: String? = "",
		@SerializedName("order") val order: Int? = 0,
		@SerializedName("visible") val visible: Int? = 0
)
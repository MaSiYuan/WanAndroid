package cn.msy.wanandroid.mvp.ui.adapter

import cn.msy.wanandroid.R
import cn.msy.wanandroid.mvp.model.resp.Article
import com.blankj.utilcode.util.TimeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.home_recycle_item.*
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * 首页列表适配器
 * @author msy
 */
class HomeAdapter(layoutResId: Int) : BaseQuickAdapter<Article, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        helper?.setText(R.id.tv_author, item?.author)
        helper?.setText(R.id.tv_time, TimeUtils.millis2String(item?.publishTime!!))
        helper?.setText(R.id.tv_title, item?.title)
        helper?.setText(R.id.tv_origin, item?.origin)
    }

}
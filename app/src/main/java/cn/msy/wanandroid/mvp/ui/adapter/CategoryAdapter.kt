package cn.msy.wanandroid.mvp.ui.adapter

import cn.msy.wanandroid.R
import cn.msy.wanandroid.mvp.model.resp.Category
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 知识体系
 * @author msy
 */
class CategoryAdapter(layoutResId: Int) : BaseQuickAdapter<Category, BaseViewHolder>(layoutResId) {

    var sb: StringBuffer? = null

    override fun convert(helper: BaseViewHolder?, item: Category?) {
        helper?.setText(R.id.tv_title, item?.name)

        sb = StringBuffer()
        for (i in item?.children?.indices!!) {
            sb?.append(item.children[i]?.name)
            if (i < item.children.size - 1) {
                sb?.append("\n")
            }
        }

        helper?.setText(R.id.tv_content, sb.toString())
    }
}
package cn.msy.wanandroid.mvp.ui.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.msy.wanandroid.mvp.model.resp.SubCategory
import cn.msy.wanandroid.mvp.ui.fragment.CategoryContentFragment

/**
 * @author msy
 */
class SubCategoryAdapter(fm: FragmentManager, list: List<SubCategory?>?) : FragmentPagerAdapter(fm) {

    var listFragment = ArrayList<CategoryContentFragment>()
    var data: List<SubCategory?>? = null

    init {
        data = list
        list?.forEach {
            listFragment.add(CategoryContentFragment.newInstance(it?.id))
        }
    }

    override fun getItem(position: Int): CategoryContentFragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data!![position]?.name
    }

}
package cn.msy.wanandroid.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.msy.wanandroid.mvp.ui.fragment.CategoryFragment
import cn.msy.wanandroid.mvp.ui.fragment.HomeFragment
import cn.msy.wanandroid.mvp.ui.fragment.UserFragment

/**
 * 首页ViewPager适配器
 * @author msy
 */
class MainAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    val HOME: Int = 0
    val CATEGORY: Int = 1
    val USER: Int = 2

    override fun getItem(position: Int): Fragment {
        when (position) {
            HOME -> {
                return HomeFragment.newInstance()
            }
            CATEGORY -> {
                return CategoryFragment.newInstance()
            }
            USER -> {
                return UserFragment.newInstance()
            }
        }
        return HomeFragment.newInstance()
    }

    override fun getCount(): Int {
        return 3
    }

}
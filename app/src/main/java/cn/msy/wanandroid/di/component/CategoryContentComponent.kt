package cn.msy.wanandroid.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import cn.msy.wanandroid.di.module.CategoryContentModule

import cn.msy.wanandroid.mvp.ui.fragment.CategoryContentFragment

@ActivityScope
@Component(modules = arrayOf(CategoryContentModule::class), dependencies = arrayOf(AppComponent::class))
interface CategoryContentComponent {
    fun inject(fragment: CategoryContentFragment)
}
package cn.msy.wanandroid.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import cn.msy.wanandroid.di.module.CategoryModule

import cn.msy.wanandroid.mvp.ui.fragment.CategoryFragment

@ActivityScope
@Component(modules = arrayOf(CategoryModule::class), dependencies = arrayOf(AppComponent::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}
package cn.msy.wanandroid.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import cn.msy.wanandroid.di.module.SubCategoryModule

import cn.msy.wanandroid.mvp.ui.activity.SubCategoryActivity

@ActivityScope
@Component(modules = arrayOf(SubCategoryModule::class), dependencies = arrayOf(AppComponent::class))
interface SubCategoryComponent {
    fun inject(activity: SubCategoryActivity)
}
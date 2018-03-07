package cn.msy.wanandroid.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import cn.msy.wanandroid.di.module.ContentModule

import cn.msy.wanandroid.mvp.ui.activity.ContentActivity

@ActivityScope
@Component(modules = arrayOf(ContentModule::class), dependencies = arrayOf(AppComponent::class))
interface ContentComponent {
    fun inject(activity: ContentActivity)
}
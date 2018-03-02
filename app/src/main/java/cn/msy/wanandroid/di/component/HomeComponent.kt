package cn.msy.wanandroid.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import cn.msy.wanandroid.di.module.HomeModule

import cn.msy.wanandroid.mvp.ui.fragment.HomeFragment

@ActivityScope
@Component(modules = arrayOf(HomeModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}
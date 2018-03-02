package cn.msy.wanandroid.di.component

import com.jess.arms.di.scope.ActivityScope

import dagger.Component

import com.jess.arms.di.component.AppComponent

import cn.msy.wanandroid.di.module.UserModule

import cn.msy.wanandroid.mvp.ui.fragment.UserFragment

@ActivityScope
@Component(modules = arrayOf(UserModule::class), dependencies = arrayOf(AppComponent::class))
interface UserComponent {
    fun inject(fragment: UserFragment)
}
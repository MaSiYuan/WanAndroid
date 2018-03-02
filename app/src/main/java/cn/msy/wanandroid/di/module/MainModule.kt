package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.MainContract
import cn.msy.wanandroid.mvp.model.MainModel


@Module
class MainModule
/**
 * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: MainContract.View) {

    @ActivityScope
    @Provides
    internal fun provideMainView(): MainContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideMainModel(model: MainModel): MainContract.Model {
        return model
    }
}
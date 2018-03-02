package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.HomeContract
import cn.msy.wanandroid.mvp.model.HomeModel


@Module
class HomeModule
/**
 * 构建HomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: HomeContract.View) {

    @ActivityScope
    @Provides
    internal fun provideHomeView(): HomeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideHomeModel(model: HomeModel): HomeContract.Model {
        return model
    }
}
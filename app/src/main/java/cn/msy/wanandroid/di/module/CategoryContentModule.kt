package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.CategoryContentContract
import cn.msy.wanandroid.mvp.model.CategoryContentModel


@Module
class CategoryContentModule
/**
 * 构建CategoryContentModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: CategoryContentContract.View) {

    @ActivityScope
    @Provides
    internal fun provideCategoryContentView(): CategoryContentContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideCategoryContentModel(model: CategoryContentModel): CategoryContentContract.Model {
        return model
    }
}
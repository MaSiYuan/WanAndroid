package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.CategoryContract
import cn.msy.wanandroid.mvp.model.CategoryModel


@Module
class CategoryModule
/**
 * 构建CategoryModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: CategoryContract.View) {

    @ActivityScope
    @Provides
    internal fun provideCategoryView(): CategoryContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideCategoryModel(model: CategoryModel): CategoryContract.Model {
        return model
    }
}
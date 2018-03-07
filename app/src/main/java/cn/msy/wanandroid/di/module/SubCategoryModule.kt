package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.SubCategoryContract
import cn.msy.wanandroid.mvp.model.SubCategoryModel


@Module
class SubCategoryModule
/**
 * 构建SubCategoryModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: SubCategoryContract.View) {

    @ActivityScope
    @Provides
    internal fun provideSubCategoryView(): SubCategoryContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideSubCategoryModel(model: SubCategoryModel): SubCategoryContract.Model {
        return model
    }
}
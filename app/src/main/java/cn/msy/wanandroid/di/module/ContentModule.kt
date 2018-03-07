package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.ContentContract
import cn.msy.wanandroid.mvp.model.ContentModel


@Module
class ContentModule
/**
 * 构建ContentModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: ContentContract.View) {

    @ActivityScope
    @Provides
    internal fun provideContentView(): ContentContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideContentModel(model: ContentModel): ContentContract.Model {
        return model
    }
}
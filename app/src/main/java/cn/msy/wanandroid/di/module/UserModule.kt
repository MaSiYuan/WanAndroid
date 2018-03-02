package cn.msy.wanandroid.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import cn.msy.wanandroid.mvp.contract.UserContract
import cn.msy.wanandroid.mvp.model.UserModel


@Module
class UserModule
/**
 * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter

 * @param view
 */
(private val view: UserContract.View) {

    @ActivityScope
    @Provides
    internal fun provideUserView(): UserContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    internal fun provideUserModel(model: UserModel): UserContract.Model {
        return model
    }
}
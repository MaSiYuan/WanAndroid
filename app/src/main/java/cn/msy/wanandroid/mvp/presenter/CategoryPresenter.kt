package cn.msy.wanandroid.mvp.presenter

import android.app.Application
import cn.msy.wanandroid.mvp.contract.CategoryContract
import cn.msy.wanandroid.mvp.model.resp.CategoryResp
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject


@ActivityScope
class CategoryPresenter @Inject
constructor(model: CategoryContract.Model, rootView: CategoryContract.View, private var mErrorHandler: RxErrorHandler?, private var mApplication: Application?, private var mImageLoader: ImageLoader?, private var mAppManager: AppManager?) : BasePresenter<CategoryContract.Model, CategoryContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }

    fun getCategory() {
        mModel.getCategory()
                .subscribeOn(Schedulers.io())
                .retryWhen(RetryWithDelay(3, 2))
                .doOnSubscribe { mRootView.showLoading() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(object : ErrorHandleSubscriber<CategoryResp>(mErrorHandler) {

                    override fun onNext(t: CategoryResp?) {
                        mRootView.hideLoading()
                        if (t?.errorCode == 0) {
                            mRootView.showCategory(t.data)
                        } else {
                            mRootView.showMessage(t?.errorMsg)
                        }
                    }

                    override fun onError(t: Throwable?) {
                        mRootView.showMessage("加载失败了")
                        mRootView.hideLoading()
                    }

                })
    }

}

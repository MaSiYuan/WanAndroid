package cn.msy.wanandroid.mvp.presenter

import android.app.Application
import cn.msy.wanandroid.mvp.contract.CategoryContentContract
import cn.msy.wanandroid.mvp.model.resp.ArticleResp
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
class CategoryContentPresenter @Inject
constructor(model: CategoryContentContract.Model, rootView: CategoryContentContract.View, private var mErrorHandler: RxErrorHandler?, private var mApplication: Application?, private var mImageLoader: ImageLoader?, private var mAppManager: AppManager?) : BasePresenter<CategoryContentContract.Model, CategoryContentContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }

    fun getSubCategory(index: Int?, cid: Int?) {
        mModel.getSubCategory(index, cid)
                .subscribeOn(Schedulers.io())
                .retryWhen(RetryWithDelay(3, 2))
                .doOnSubscribe { mRootView.showLoading() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(object : ErrorHandleSubscriber<ArticleResp>(mErrorHandler) {

                    override fun onNext(t: ArticleResp?) {
                        mRootView.hideLoading()
                        if (t?.errorCode == 0) {
                            mRootView.showSubCategory(t.data?.datas)
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

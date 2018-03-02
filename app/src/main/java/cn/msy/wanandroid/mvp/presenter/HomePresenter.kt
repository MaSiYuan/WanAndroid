package cn.msy.wanandroid.mvp.presenter

import android.app.Application
import cn.msy.wanandroid.mvp.contract.HomeContract
import cn.msy.wanandroid.mvp.model.resp.ArticleResp
import cn.msy.wanandroid.mvp.model.resp.BannerResp
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
class HomePresenter @Inject
constructor(model: HomeContract.Model, rootView: HomeContract.View, private var mErrorHandler: RxErrorHandler?, private var mApplication: Application?, private var mImageLoader: ImageLoader?, private var mAppManager: AppManager?) : BasePresenter<HomeContract.Model, HomeContract.View>(model, rootView) {

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }

    fun getArticle(pageIndex: Int) {
        mModel.getArticle(pageIndex)
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
                            mRootView.showArticle(t.data?.datas)
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

    fun getBanner() {
        mModel.getBanner()
                .subscribeOn(Schedulers.io())
                .retryWhen(RetryWithDelay(3, 2))
                .doOnSubscribe { mRootView.showLoading() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(object : ErrorHandleSubscriber<BannerResp>(mErrorHandler) {

                    override fun onNext(t: BannerResp?) {
                        mRootView.hideLoading()
                        if (t?.errorCode == 0) {
                            mRootView.showBanner(t.data)
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

package cn.msy.wanandroid.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import cn.msy.wanandroid.R
import cn.msy.wanandroid.di.component.DaggerSubCategoryComponent
import cn.msy.wanandroid.di.module.SubCategoryModule
import cn.msy.wanandroid.mvp.contract.SubCategoryContract
import cn.msy.wanandroid.mvp.presenter.SubCategoryPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull


class SubCategoryActivity : BaseActivity<SubCategoryPresenter>(), SubCategoryContract.View {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSubCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .subCategoryModule(SubCategoryModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_sub_category //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }


}

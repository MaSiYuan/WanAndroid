package cn.msy.wanandroid.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import cn.msy.wanandroid.R
import cn.msy.wanandroid.di.component.DaggerSubCategoryComponent
import cn.msy.wanandroid.di.module.SubCategoryModule
import cn.msy.wanandroid.mvp.contract.SubCategoryContract
import cn.msy.wanandroid.mvp.model.resp.Category
import cn.msy.wanandroid.mvp.model.resp.SubCategory
import cn.msy.wanandroid.mvp.presenter.SubCategoryPresenter
import cn.msy.wanandroid.mvp.ui.adapter.SubCategoryAdapter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import kotlinx.android.synthetic.main.activity_sub_category.*


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
        val category: Category = intent.getSerializableExtra("data") as Category
        tool_bar.title = category.name

        tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
        val adapter:SubCategoryAdapter = SubCategoryAdapter(supportFragmentManager,category.children)
        vp_sub_category.adapter = adapter
        tab_layout.setupWithViewPager(vp_sub_category,true)

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

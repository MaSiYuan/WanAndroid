package cn.msy.wanandroid.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import cn.msy.wanandroid.di.component.DaggerCategoryComponent
import cn.msy.wanandroid.di.module.CategoryModule
import cn.msy.wanandroid.mvp.contract.CategoryContract
import cn.msy.wanandroid.mvp.presenter.CategoryPresenter

import cn.msy.wanandroid.R
import cn.msy.wanandroid.mvp.model.resp.Category
import cn.msy.wanandroid.mvp.ui.activity.MainActivity
import cn.msy.wanandroid.mvp.ui.activity.SubCategoryActivity
import cn.msy.wanandroid.mvp.ui.adapter.CategoryAdapter
import com.chad.library.adapter.base.BaseQuickAdapter

import com.jess.arms.utils.Preconditions.checkNotNull
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_home.*


class CategoryFragment : BaseFragment<CategoryPresenter>(), CategoryContract.View {

    val adapter: CategoryAdapter = CategoryAdapter(R.layout.category_recycle_item)

    override fun showCategory(category: List<Category?>?) {
        adapter.setNewData(category)
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .categoryModule(CategoryModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        rv_category.adapter = adapter
        rv_category.onHRecyclerViewScrolledListener = activity as MainActivity

        adapter.setOnItemClickListener { adapter, view, position ->
            val category: Category = adapter.getItem(position) as Category
            Intent(activity, SubCategoryActivity::class.java).run {
                putExtra("data", category)
                startActivity(this)
            }
        }

        mPresenter.getCategory()
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     *
     *
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了

     * @param data
     */

    override fun setData(data: Any) {

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

    }

    companion object {


        fun newInstance(): CategoryFragment {
            val fragment = CategoryFragment()
            return fragment
        }
    }

}

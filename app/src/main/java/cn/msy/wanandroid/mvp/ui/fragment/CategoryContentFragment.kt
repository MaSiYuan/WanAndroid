package cn.msy.wanandroid.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.msy.wanandroid.R
import cn.msy.wanandroid.di.component.DaggerCategoryContentComponent
import cn.msy.wanandroid.di.module.CategoryContentModule
import cn.msy.wanandroid.mvp.contract.CategoryContentContract
import cn.msy.wanandroid.mvp.model.resp.Article
import cn.msy.wanandroid.mvp.presenter.CategoryContentPresenter
import cn.msy.wanandroid.mvp.ui.adapter.HomeAdapter
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import kotlinx.android.synthetic.main.fragment_category_content.*


class CategoryContentFragment : BaseFragment<CategoryContentPresenter>(), CategoryContentContract.View {

    var adapter:HomeAdapter? = null

    override fun showSubCategory(list: List<Article?>?) {
        adapter?.setNewData(list)
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerCategoryContentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .categoryContentModule(CategoryContentModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category_content, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val cid:Int? = arguments?.getInt("cid")
        mPresenter.getSubCategory(0,cid)

        adapter = HomeAdapter(R.layout.home_recycle_item)
        rv_sub_category.adapter = adapter
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

        fun newInstance(cid: Int?): CategoryContentFragment {
            val fragment = CategoryContentFragment()
            val args = Bundle()
            args.putInt("cid", cid!!)
            fragment.arguments = args
            return fragment
        }
    }

}

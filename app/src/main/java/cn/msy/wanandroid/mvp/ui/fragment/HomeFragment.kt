package cn.msy.wanandroid.mvp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.msy.wanandroid.R
import cn.msy.wanandroid.di.component.DaggerHomeComponent
import cn.msy.wanandroid.di.module.HomeModule
import cn.msy.wanandroid.mvp.contract.HomeContract
import cn.msy.wanandroid.mvp.model.resp.Article
import cn.msy.wanandroid.mvp.model.resp.Banner
import cn.msy.wanandroid.mvp.presenter.HomePresenter
import cn.msy.wanandroid.mvp.ui.adapter.HomeAdapter
import com.bumptech.glide.Glide
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.http.imageloader.glide.GlideArms
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomePresenter>(), HomeContract.View {

    var adapter: HomeAdapter? = null

    override fun showArticle(articles: List<Article?>?) {
        adapter?.setNewData(articles)
    }

    override fun showBanner(banners: List<Banner?>?) {
        val banner = adapter?.headerLayout?.findViewById<com.youth.banner.Banner>(R.id.banner)
        banner
                ?.setImages(banners)
                ?.setImageLoader(object : ImageLoader() {
                    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                        Glide.with(activity!!)
                                .load((path as Banner).imagePath)
                                .into(imageView!!)
                    }
                })
                ?.start()
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(HomeModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        adapter = HomeAdapter(R.layout.home_recycle_item)
        val header: View = LayoutInflater.from(context).inflate(R.layout.home_banner_view, rv_home, false)
        adapter?.addHeaderView(header)
        rv_home.adapter = adapter

        mPresenter.getArticle(0)
        mPresenter.getBanner()
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

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

}

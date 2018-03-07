package cn.msy.wanandroid.mvp.ui.activity


import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import cn.msy.wanandroid.R
import cn.msy.wanandroid.di.component.DaggerMainComponent
import cn.msy.wanandroid.di.module.MainModule
import cn.msy.wanandroid.mvp.contract.MainContract
import cn.msy.wanandroid.mvp.presenter.MainPresenter
import cn.msy.wanandroid.mvp.ui.adapter.MainAdapter
import cn.msy.wanandroid.widget.ScrollVRecyclerView
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import devlight.io.library.ntb.NavigationTabBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter>(), MainContract.View, ScrollVRecyclerView.OnHRecyclerViewScrolledListener {

    override fun bottomBarShow() {
//        ntb_home.visibility = View.VISIBLE
//        ObjectAnimator.ofFloat(ntb_home, "translateY", 0f, ntb_home.height.toFloat())
//                .start()
    }

    override fun bottomBarHide() {
//        ntb_home.visibility = View.GONE
//        ObjectAnimator.ofFloat(ntb_home, "translateY", ntb_home.height.toFloat(), 0f)
//                .start()
    }

    val models: ArrayList<NavigationTabBar.Model> = ArrayList()
    val adapter: MainAdapter = MainAdapter(supportFragmentManager)

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initData(savedInstanceState: Bundle?) {
        vp_home.adapter = adapter
        models.add(
                NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_black_24dp),
                        Color.parseColor("#FF4081")
                ).title("首页")
                        .badgeTitle("NTB")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_black_24dp),
                        Color.parseColor("#FF4081")
                ).title("知识")
                        .badgeTitle("NTB")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_black_24dp),
                        Color.parseColor("#FF4081")
                ).title("我的")
                        .badgeTitle("NTB")
                        .build()
        )
        ntb_home.models = models
        ntb_home.setViewPager(vp_home)
        ntb_home.isBehaviorEnabled = true
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

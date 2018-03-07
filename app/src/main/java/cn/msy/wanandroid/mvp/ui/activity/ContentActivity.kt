package cn.msy.wanandroid.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.widget.LinearLayout
import cn.msy.wanandroid.R
import cn.msy.wanandroid.di.component.DaggerContentComponent
import cn.msy.wanandroid.di.module.ContentModule
import cn.msy.wanandroid.mvp.contract.ContentContract
import cn.msy.wanandroid.mvp.presenter.ContentPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_content.*


class ContentActivity : BaseActivity<ContentPresenter>(), ContentContract.View {

    var title: String? = null
    var url: String? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerContentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .contentModule(ContentModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_content //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initData(savedInstanceState: Bundle?) {
        title = intent.getStringExtra("title")
        url = intent.getStringExtra("url")

        tool_bar.title = title
        tool_bar.setNavigationOnClickListener {
            killMyself()
        }

        val agentWeb:AgentWeb = AgentWeb.with(this)
                .setAgentWebParent(web_content, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url)

        val webSetting:WebSettings = agentWeb.webCreator.webView.settings
        webSetting.useWideViewPort = true
        webSetting.loadWithOverviewMode = true
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

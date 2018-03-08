package cn.msy.wanandroid.mvp.contract

import cn.msy.wanandroid.mvp.model.resp.Article
import cn.msy.wanandroid.mvp.model.resp.ArticleResp
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable


interface CategoryContentContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{
        fun showSubCategory(list:List<Article?>?)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getSubCategory(index: Int?, cid: Int?):Observable<ArticleResp>
    }
}

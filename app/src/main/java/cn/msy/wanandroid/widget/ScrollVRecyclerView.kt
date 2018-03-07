package cn.msy.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log

/**
 * 带滚动监听的RecyclerView
 * @author msy
 */
class ScrollVRecyclerView : VRecyclerView {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
//        Log.e("ScrollVRecyclerView", "current=" + dy)
        if (dy < 0) {
            if (Math.abs(dy) > 10) {
                //显示
                Log.e("ScrollVRecyclerView", "show" + dy)
                onHRecyclerViewScrolledListener?.bottomBarShow()
            }
        } else {
            if (Math.abs(dy) > 10) {
                //隐藏
                Log.e("ScrollVRecyclerView", "hide" + dy)
                onHRecyclerViewScrolledListener?.bottomBarHide()
            }
        }
    }

    var onHRecyclerViewScrolledListener: OnHRecyclerViewScrolledListener? = null

    interface OnHRecyclerViewScrolledListener {
        fun bottomBarShow()
        fun bottomBarHide()
    }

}
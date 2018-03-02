package cn.msy.wanandroid.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * 水平方向RecyclerView
 * @author msy
 */
class HRecyclerView : RecyclerView {

    constructor(context: Context) : super(context) {
        setHorizontalManager()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setHorizontalManager()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setHorizontalManager()
    }

    fun setHorizontalManager() {
        val manager: LayoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        layoutManager = manager
    }
}
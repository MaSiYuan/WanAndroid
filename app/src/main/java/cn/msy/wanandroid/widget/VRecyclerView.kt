package cn.msy.wanandroid.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * 垂直方向RecyclerView
 * @author msy
 */
open class VRecyclerView : RecyclerView {
    constructor(context: Context) : super(context) {
        setVerticalManager()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setVerticalManager()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setVerticalManager()
    }

    fun setVerticalManager() {
        val manager: LinearLayoutManager = LinearLayoutManager(context, VERTICAL, false)
        layoutManager = manager
    }
}
package cn.msy.wanandroid.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import cn.msy.wanandroid.R

/**
 * 多状态布局
 * @author msy
 */
class MultiStateLayout : FrameLayout {

    var viewLoading: View? = null
    var viewLoadFailed: View? = null
    var viewNoData: View? = null
    var viewNoNetwork: View? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun parseAttrs(context: Context?, attrs: AttributeSet?) {

        val typedArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.MultiStateLayout, 0, 0)
        val viewIdLoading = typedArray?.getResourceId(R.styleable.MultiStateLayout_view_loading, -1)
        val viewIdLoadFailed = typedArray?.getResourceId(R.styleable.MultiStateLayout_view_load_failed, -1)
        val viewIdNoData = typedArray?.getResourceId(R.styleable.MultiStateLayout_view_no_data, -1)
        val viewIdNoNetwork = typedArray?.getResourceId(R.styleable.MultiStateLayout_view_no_network, -1)
        typedArray?.recycle()

        if (viewIdLoading != -1 && viewIdLoading != null) {
            viewLoading = LayoutInflater.from(context).inflate(viewIdLoading, this, false)
        }

        if (viewIdLoadFailed != -1 && viewIdLoadFailed != null) {
            viewLoadFailed = LayoutInflater.from(context).inflate(viewIdLoadFailed, this, false)
        }

        if (viewIdNoData != -1 && viewIdNoData != null) {
            viewNoData = LayoutInflater.from(context).inflate(viewIdNoData, this, false)
        }

        if (viewIdNoNetwork != -1 && viewIdNoNetwork != null) {
            viewNoNetwork = LayoutInflater.from(context).inflate(viewIdNoNetwork, this, false)
        }
    }

    /**
     * 功能简述: 加载中
     */
    fun showLoading() {
        withAnimation(viewLoading)
    }

    /**
     * 功能简述: 加载成功
     */
    fun showLoadSuccess() {
        removeView(viewLoadFailed)
        removeView(viewNoData)
        removeView(viewNoNetwork)
        removeView(viewLoading)
    }

    /**
     * 功能简述: 加载失败
     */
    fun showLoadFailed() {
        withAnimation(viewLoadFailed)
    }

    /**
     * 功能简述: 加载无数据
     */
    fun showNoData() {
        withAnimation(viewNoData)
    }

    /**
     * 功能简述: 无网络
     */
    fun showNoNetwork() {
        withAnimation(viewNoNetwork)
    }

    private fun withAnimation(view: View?) {
        removeViews()
        addView(view)

        val set = AnimatorSet()
        var scaleX: ObjectAnimator? = null
        var scaleY: ObjectAnimator? = null

        if (view != null) {
            scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f)
        }

        set.duration = 200
        set.interpolator = DecelerateInterpolator()
        set.play(scaleX).with(scaleY)
        set.start()
    }

    private fun removeViews() {
        removeView(viewLoading)
        removeView(viewLoadFailed)
        removeView(viewNoNetwork)
        removeView(viewNoData)
    }

}
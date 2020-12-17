package com.sec.recyclerview.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.internal.InternalAbstract
import com.sec.recyclerview.R
import kotlinx.android.synthetic.main.lottie_footer.view.*

class LottieFooter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : InternalAbstract(context, attrs, defStyleAttr), RefreshFooter {

    init {
        View.inflate(context, R.layout.lottie_footer, this)
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        super.onStartAnimator(refreshLayout, height, maxDragHeight)
        lottieView.playAnimation()
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        lottieView.cancelAnimation()
        return super.onFinish(refreshLayout, success)
    }

    override fun setNoMoreData(noMoreData: Boolean): Boolean = when (noMoreData) {
        true -> {
            lottieView.visibility = View.GONE
            more.visibility = View.VISIBLE
            true
        }
        else -> {
            lottieView.visibility = View.VISIBLE
            more.visibility = View.GONE
            true
        }
    }

}
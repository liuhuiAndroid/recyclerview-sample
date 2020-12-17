package com.sec.recyclerview

class MainSupport {

    /**
     * 简单点击
     */
    var clickCallback: ((Int, Int) -> Unit)? = null

    /**
     * 错误页面item点击触发重试功能，
     */
    var retry: (() -> Unit)? = null

    infix fun onClickCallback(block: (Int, Int) -> Unit) {
        clickCallback = block
    }

    infix fun onRetry(block: () -> Unit) {
        retry = block
    }

}

inline fun createMainAdapter(
    crossinline support: MainSupport.() -> Unit
): MainAdapter {
    return MainAdapter(MainSupport().apply(support))
}

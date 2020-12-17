package com.sec.recyclerview

class MainSupport {

    /**
     * 简单点击
     */
    private var clickCallback: ((Int, Int) -> Unit)? = null

    fun invokeClickCallback(position: Int, type: Int) {
        clickCallback?.invoke(position, type)
    }

    infix fun onClickCallback(block: (Int, Int) -> Unit) {
        clickCallback = block
    }

}

inline fun createMainAdapter(
    crossinline support: MainSupport.() -> Unit
): MainAdapter {
    return MainAdapter(MainSupport().apply(support))
}

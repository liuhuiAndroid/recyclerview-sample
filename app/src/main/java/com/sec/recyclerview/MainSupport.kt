package com.sec.recyclerview

class MainSupport {

    private var clickCallback: ((Int, Int) -> Unit)? = null

    fun invokeClickCallback(position: Int, type: Int) {
        clickCallback?.invoke(position, type)
    }

    infix fun onClickCallback(block: (Int, Int) -> Unit) {
        clickCallback = block
    }

}
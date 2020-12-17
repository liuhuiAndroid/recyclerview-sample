package com.sec.recyclerview.ext

internal inline fun <reified T> Any.sameAs(invoke: (T) -> Unit = {}): Boolean {
    if (this is T) {
        invoke.invoke(this)
        return true
    }
    return false
}

internal inline fun <reified T> MutableList<T>.containsEntity(t: T, invoke: () -> Unit = {}): Boolean {
    if (!isNullOrEmpty()) {
        val contains = contains(t)
        if (contains)
            invoke.invoke()
        return contains
    }
    return false
}
package com.rentez.data.cache.base

import io.reactivex.subjects.PublishSubject

abstract class MemoryCache<T>(private var item: T) {
    private val publishSubject = PublishSubject.create<T>()

    fun get() = item

    open fun update(item: T){
        if (item == null) return
        this.item = item
        publishSubject.onNext(item)
    }

    fun listen() = publishSubject
}
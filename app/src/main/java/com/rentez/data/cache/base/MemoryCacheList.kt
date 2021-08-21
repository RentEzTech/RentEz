package com.rentez.data.cache.base

import com.rentez.models.ItemChange
import com.rentez.models.ItemChange.Companion.ITEM_ADD
import com.rentez.models.ItemChange.Companion.ITEM_REMOVE
import com.rentez.models.ItemChange.Companion.ITEM_UPDATE
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.CopyOnWriteArrayList

abstract class MemoryCacheList<T>(private val comparator: Comparator<T>) {

    protected var list: MutableList<T> = CopyOnWriteArrayList()

    private val publishSubject = PublishSubject.create<ItemChange<T>>()

    fun get() = list

    fun put(item: T) {
        var change = ITEM_UPDATE
        var index = list.binarySearch(item,comparator)
        if (index != 0 && index >= list.size)return

        if (index<0){
            list.add(item)
            list.sortWith(comparator)
            index = list.binarySearch(item, comparator)
            change = ITEM_ADD
        }else{
            list[index] = item
        }
        val newChange = ItemChange(change,index,item)
        publishSubject.onNext(newChange)
    }

    fun set(index: Int, item:T){
        list[index] = item
        val change = ItemChange(ITEM_UPDATE,index,item)
        publishSubject.onNext(change)
    }

    fun remove(item: T){
        val index = list.binarySearch(item, comparator)
        if (index<0)return
        list.removeAt(index)
        val change = ItemChange(ITEM_REMOVE,index,item)
        publishSubject.onNext(change)
    }

    fun updates() = publishSubject

    fun clear(){
        val deletedItems = mutableListOf<T>()
        list.forEach {
            deletedItems.add(it)
        }
        deletedItems.forEach {
            remove(it)
        }
        list.clear()
    }
}
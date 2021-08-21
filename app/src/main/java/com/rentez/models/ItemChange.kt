package com.rentez.models

import androidx.annotation.IntDef

data class ItemChange<T>(
    @Change
    val change: Int = 0,
    val position: Int = -1,
    val item: T
) {

    @IntDef(ITEM_ADD, ITEM_REMOVE, ITEM_UPDATE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Change

    companion object {
        const val ITEM_ADD = 0
        const val ITEM_REMOVE = 1
        const val ITEM_UPDATE = 2
    }
}
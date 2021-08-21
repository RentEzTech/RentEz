package com.rentez.data

import com.rentez.data.cache.CacheDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val mockDataSource: MockDataSource
) {
}
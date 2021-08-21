package com.rentez.data

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject

class MockDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
}
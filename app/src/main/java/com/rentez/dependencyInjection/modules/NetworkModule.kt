package com.rentez.dependencyInjection.modules

import android.app.Application
import com.google.gson.GsonBuilder
import com.rentez.dependencyInjection.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import java.lang.reflect.Modifier

@Module
class NetworkModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun provideGson() = GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create()

    @ApplicationScope
    @Provides
    fun provideOkHttpCache() = Cache(application.cacheDir, CACHE_SIZE)

    companion object {
        const val CACHE_SIZE = 20 * 2048L
    }
}
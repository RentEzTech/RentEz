package com.rentez

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.rentez.dependencyInjection.components.ApplicationComponent
import com.rentez.dependencyInjection.components.DaggerApplicationComponent
import com.rentez.dependencyInjection.modules.ApplicationModule
import com.rentez.dependencyInjection.modules.CacheModule
import com.rentez.dependencyInjection.modules.NetworkModule
import io.reactivex.plugins.RxJavaPlugins

class App : Application(), LifecycleObserver {

    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .cacheModule(CacheModule(this))
            .networkModule(NetworkModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        RxJavaPlugins.setErrorHandler { setGlobalRxErrorHandler(it) }
    }

    private fun setGlobalRxErrorHandler(throwable: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }
}
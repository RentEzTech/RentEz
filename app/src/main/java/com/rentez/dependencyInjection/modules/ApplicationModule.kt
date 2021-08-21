package com.rentez.dependencyInjection.modules

import android.app.Application
import androidx.preference.PreferenceManager
import com.rentez.dependencyInjection.scope.ApplicationScope
import com.rentez.models.ApplicationState
import com.rentez.utils.RxBus
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class ApplicationModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun provideApplicationContext() = application.applicationContext

    @ApplicationScope
    @Provides
    fun provideSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(application.applicationContext)

    @ApplicationScope
    @Provides
    fun providesRxBus(publisher : PublishSubject<Any>) = RxBus (publisher)

    @ApplicationScope
    @Provides
    fun providesDisplayMetrics() = application.resources.displayMetrics

    @ApplicationScope
    @Provides
    fun providesPublishSubject() = PublishSubject.create<Any>()

    @ApplicationScope
    @Provides
    fun providesApplicationState() = ApplicationState()

    //TODO provides data repositiory
}
package com.rentez.dependencyInjection.components

import com.rentez.MainActivity
import com.rentez.dependencyInjection.modules.ApplicationModule
import com.rentez.dependencyInjection.modules.CacheModule
import com.rentez.dependencyInjection.modules.NetworkModule
import com.rentez.dependencyInjection.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class,NetworkModule::class,CacheModule::class])
interface ApplicationComponent {
    //Inject Activity
    fun inject(activity:MainActivity)

    //Inject Fragment
}
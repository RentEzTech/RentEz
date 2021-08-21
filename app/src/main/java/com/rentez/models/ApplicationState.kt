package com.rentez.models

class ApplicationState {
    private var appStateMap = hashMapOf<String, Any?>()

    fun <T> getAppState(clazz: Class<T>): T {
        val className = clazz.simpleName
        if (appStateMap[className] == null) {
            appStateMap[className] = clazz.getDeclaredConstructor().newInstance()
        }
        return clazz.cast(appStateMap[className])!!
    }

    fun clear() {
        appStateMap.clear()
    }
}
package com.rentez.ui.base

interface BaseContract {

    interface View{

    }
    interface Presenter<V: View>{

        fun getView(): V?

        fun attachView(view: V)

        fun detachView()

        fun onPresenterCreated()

        fun onPresenterDestroyed()

        fun onCreated()

        fun onStarted()

        fun onResumed()

        fun onDestroyed()

        fun onStopped()

        fun onPaused()

        fun onBackPressed() :Boolean

        fun onSavedInsatnce(hasSavedInstance: Boolean)
    }
}
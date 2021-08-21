package com.rentez.ui.base

import androidx.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V>, LifecycleObserver {
    protected var disposabels: CompositeDisposable? = CompositeDisposable()
    protected var view: WeakReference<V>? = null
    protected var hasSavedInstance = false

    override fun getView(): V? = view?.get()

    override fun attachView(view: V) {
        this.view = WeakReference(view)
    }

    override fun detachView() {
        view?.clear()
        view = null
    }

    override fun onPresenterCreated() {
        this.disposabels = CompositeDisposable()
    }

    override fun onPresenterDestroyed() {
        disposabels?.dispose()
        disposabels = null
    }

    override fun onCreated() {

    }

    override fun onStarted() {

    }

    override fun onResumed() {

    }

    override fun onStopped() {

    }

    override fun onPaused() {

    }

    override fun onBackPressed(): Boolean = false

    override fun onSavedInsatnce(hasSavedInstance: Boolean) {
        this.hasSavedInstance = hasSavedInstance
    }

    override fun onDestroyed() {

    }
}
package com.rentez.utils

import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

open class RxBus @Inject constructor(private val publisher: PublishSubject<Any>) {

    var behaviors = mutableMapOf<String, BehaviorSubject<Any>>()

    @Synchronized
    private fun getBehaviour(className: String): BehaviorSubject<Any> {
        var behaviourInternal = behaviors[className]
        if (behaviourInternal == null) {
            behaviourInternal = BehaviorSubject.create()
            behaviors[className] = behaviourInternal
        }
        return behaviourInternal
    }

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun publishSticky(event: Any) {
        getBehaviour(event.javaClass.name).onNext(event)
    }

    fun <T> listen(eventType: Class<T>) = publisher.ofType(eventType)

    fun <T> listenSticky(eventType: Class<T>) = getBehaviour(eventType.name).ofType(eventType)

    fun clear() = behaviors.clear()
}
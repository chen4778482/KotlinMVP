package txcom.gsi.kotlinmvp.utils

import javax.inject.Inject
import javax.inject.Singleton

import rx.Observable
import rx.subjects.PublishSubject

/**
 * Created by Administrator on 2018/3/1.
 */

@Singleton
class RxEventBus @Inject
constructor() {

    private val mBusSubject: PublishSubject<Any>

    init {
        mBusSubject = PublishSubject.create()
    }

    /**
     * Posts an object (usually an Event) to the bus
     */
    fun post(event: Any) {
        mBusSubject.onNext(event)
    }

    /**
     * Observable that will emmit everything posted to the event bus.
     */
    fun observable(): Observable<Any> {
        return mBusSubject
    }

    /**
     * Observable that only emits events of a specific class.
     * Use this if you only want to subscribe to one type of events.
     */
    fun <T> filteredObservable(eventClass: Class<T>): Observable<T> {
        return mBusSubject.ofType(eventClass)
    }

}

package txcom.gsi.kotlinmvp.utils

import rx.Subscription

object RxUtil {

    fun unsubscribe(subscription: Subscription?) {
        if (subscription != null && !subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}

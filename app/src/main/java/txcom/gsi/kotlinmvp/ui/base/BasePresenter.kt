package txcom.gsi.kotlinmvp.ui.base

import rx.Subscriber
import txcom.gsi.kotlinmvp.data.mode.Base
import txcom.gsi.kotlinmvp.data.mode.ResponseErrorCode

/**
 * Created by Administrator on 2018/3/9.
 */


open class BasePresenter<T : MvpView> : Presenter<T> {

    var mMvpView: T? = null

    val isViewAttached: Boolean
        get() = mMvpView != null

     override fun attachView(mvpView: T) {
        this.mMvpView = mvpView
    }

    fun getMvpView(): T? {
        return mMvpView
    }

    override  fun detachView() {
        mMvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")

      abstract inner class WiseucSubscriber<K : Base> : Subscriber<K>() {
        override fun onCompleted() {

        }

        override fun onError(e: Throwable) {
            getMvpView()!!.handleError(ResponseErrorCode.OTHER_ERROR_CODE, e.message!!)
        }

        override fun onNext(k: K) {
            if (k.code() === ResponseErrorCode.OK_CODE) {
                handleSuccess(k)
            } else {
                mMvpView!!.handleError(k.code(), k.message())
            }
        }

        protected abstract fun handleSuccess(k: K)
    }
}


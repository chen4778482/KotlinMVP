package txcom.gsi.kotlinmvp.ui.register


import android.os.Parcel
import android.os.Parcelable
import java.util.HashMap

import javax.inject.Inject

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import txcom.gsi.kotlinmvp.data.mode.Base
import txcom.gsi.kotlinmvp.data.remode.Services
import txcom.gsi.kotlinmvp.ui.base.BasePresenter
import txcom.gsi.kotlinmvp.utils.RxUtil


internal class RegisterPresenter @Inject
constructor(private val mService: Services) : BasePresenter<RegisterMvpView>() {
    private var mSubscription: Subscription? = null

    override fun detachView() {
        super.detachView()
        RxUtil.unsubscribe(mSubscription)
    }

    fun appRegister(code: String, password: String, number: String) {
        checkViewAttached()
        RxUtil.unsubscribe(mSubscription)
        val params = HashMap<String, String>()
        params["code"] = code
        params["password"] = password
        params["phone"] = number
        mSubscription = mService.appRegister(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : WiseucSubscriber<Base>() {
                     override fun handleSuccess(base: Base) {
                        getMvpView()!!.registerOk()
                    }
                })
    }
}
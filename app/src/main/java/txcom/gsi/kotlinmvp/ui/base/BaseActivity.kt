package txcom.gsi.kotlinmvp.ui.base

import android.app.ProgressDialog
import android.os.Bundle


import com.trello.rxlifecycle.components.support.RxAppCompatActivity

import java.util.HashMap
import java.util.concurrent.atomic.AtomicLong

import timber.log.Timber
import txcom.gsi.kotlinmvp.BaseApplication
import txcom.gsi.kotlinmvp.injection.Component.ActivityComponent
import txcom.gsi.kotlinmvp.injection.Component.ConfigPersistentComponent
import txcom.gsi.kotlinmvp.injection.Component.DaggerConfigPersistentComponent
import txcom.gsi.kotlinmvp.injection.Model.ActivityModule
import txcom.gsi.kotlinmvp.utils.DialogFactory

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
open class BaseActivity : RxAppCompatActivity() {

    protected var tag = this.javaClass.simpleName

    private var mActivityComponent: ActivityComponent? = null
    private var mActivityId: Long = 0

    protected var mProgressDialog: ProgressDialog? = null

    protected fun showProgressDialog(stringRes: Int, isCancel: Boolean) {
        dismissProgressDialog()
        mProgressDialog = DialogFactory.createProgressDialog(this, stringRes)
        mProgressDialog!!.setCancelable(isCancel)
        mProgressDialog!!.show()
    }

    protected fun dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (!sComponentsMap.containsKey(mActivityId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(BaseApplication[this].component)
                    .build()
            sComponentsMap[mActivityId] = configPersistentComponent
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mActivityId)
            configPersistentComponent = sComponentsMap[mActivityId]!!
        }
        mActivityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putLong(KEY_ACTIVITY_ID, mActivityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId)
            sComponentsMap.remove(mActivityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): ActivityComponent? {
        return mActivityComponent
    }

    fun handleError(code: Int, msg: String) {
        //        dismissProgressDialog();
        Timber.e(msg)
        //        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //        if (!TextUtils.isEmpty(msg)) {
        //        }
    }

    companion object {

        private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        private val sComponentsMap = HashMap<Long, ConfigPersistentComponent>()
    }
}


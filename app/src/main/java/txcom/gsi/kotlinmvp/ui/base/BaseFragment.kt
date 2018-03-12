package txcom.gsi.kotlinmvp.ui.base

import android.content.Context
import android.os.Bundle
import com.trello.rxlifecycle.components.support.RxFragment
import timber.log.Timber
import txcom.gsi.kotlinmvp.BaseApplication
import txcom.gsi.kotlinmvp.injection.Component.ConfigPersistentComponent
import txcom.gsi.kotlinmvp.injection.Component.DaggerConfigPersistentComponent
import txcom.gsi.kotlinmvp.injection.Component.FragmentComponent
import txcom.gsi.kotlinmvp.injection.Model.FragmentModule
import java.util.HashMap
import java.util.concurrent.atomic.AtomicLong


/**
 * Created by Administrator on 2018/3/9.
 */
class BaseFragment : RxFragment() {

    private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
    private val NEXT_ID = AtomicLong(0)
    private val sComponentsMap = HashMap<Long, ConfigPersistentComponent>()

    private var mFragmentComponent: FragmentComponent? = null
    private var mFragmentId: Long = 0

    protected var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (!sComponentsMap.containsKey(mFragmentId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(BaseApplication.get(mContext!!).component)//BaseApplication.get(mContext).component
                    .build()
            sComponentsMap[mFragmentId] = configPersistentComponent
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = sComponentsMap[mFragmentId]!!
        }
        mFragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        Timber.i("Clearing ConfigPersistentComponent id=%d", mFragmentId)
        sComponentsMap.remove(mFragmentId)
        super.onDestroy()
    }

    fun fragmentComponent(): FragmentComponent? {
        return mFragmentComponent
    }

    fun handleError(code: Int, msg: String) {
        Timber.e(msg)
    }
}
package txcom.gsi.kotlinmvp

import android.app.Application
import android.content.Context

import timber.log.Timber
import txcom.gsi.kotlinmvp.injection.Component.ApplicationComponent
import txcom.gsi.kotlinmvp.injection.Component.DaggerApplicationComponent
import txcom.gsi.kotlinmvp.injection.Model.ApplicationModule


/**
 * Created by Administrator on 2018/3/1.
 */

class BaseApplication : Application() {

    internal var mApplicationComponent: ApplicationComponent? = null

    // Needed to replace the component with a test specific one
    var component: ApplicationComponent
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }
            return mApplicationComponent as ApplicationComponent
        }
        set(applicationComponent) {
            mApplicationComponent = applicationComponent
        }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        operator fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }
    }

}

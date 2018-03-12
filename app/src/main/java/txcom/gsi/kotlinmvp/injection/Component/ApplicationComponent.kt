package txcom.gsi.kotlinmvp.injection.Component

import android.app.Application
import android.content.Context
import javax.inject.Singleton

import dagger.Component
import txcom.gsi.kotlinmvp.data.local.PreferencesHelper
import txcom.gsi.kotlinmvp.data.remode.Services
import txcom.gsi.kotlinmvp.injection.ApplicationContext
import txcom.gsi.kotlinmvp.injection.Model.ApplicationModule
import txcom.gsi.kotlinmvp.utils.RxEventBus

/**
 * Created by Administrator on 2018/3/5.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
public interface ApplicationComponent {
    @ApplicationContext
    fun contrext(): Context

    fun application(): Application

    fun preferencesHelper(): PreferencesHelper

    fun eventBus(): RxEventBus

    fun service(): Services
}
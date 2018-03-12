package txcom.gsi.kotlinmvp.injection.Component


import dagger.Subcomponent
import txcom.gsi.kotlinmvp.injection.Model.ActivityModule
import txcom.gsi.kotlinmvp.injection.PerActivity
import txcom.gsi.kotlinmvp.ui.register.RegisterActivity

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: RegisterActivity)

}

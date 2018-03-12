package txcom.gsi.kotlinmvp.injection.Component


import dagger.Subcomponent
import txcom.gsi.kotlinmvp.injection.Model.FragmentModule
import txcom.gsi.kotlinmvp.injection.PerFragment

/**
 * This component inject dependencies to all Activities across the application
 */
@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent

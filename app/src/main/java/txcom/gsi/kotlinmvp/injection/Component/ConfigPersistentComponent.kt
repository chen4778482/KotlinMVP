package txcom.gsi.kotlinmvp.injection.Component


import dagger.Component
import txcom.gsi.kotlinmvp.injection.ConfigPersistent
import txcom.gsi.kotlinmvp.injection.Model.ActivityModule
import txcom.gsi.kotlinmvp.injection.Model.FragmentModule


@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent
    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

}
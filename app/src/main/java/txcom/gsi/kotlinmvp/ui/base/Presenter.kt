package txcom.gsi.kotlinmvp.ui.base


interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}

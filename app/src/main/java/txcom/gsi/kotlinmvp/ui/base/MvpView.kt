package txcom.gsi.kotlinmvp.ui.base

/**
 * Created by Administrator on 2018/3/1.
 */

interface MvpView {
    fun handleError(code: Int, msg: String)
}

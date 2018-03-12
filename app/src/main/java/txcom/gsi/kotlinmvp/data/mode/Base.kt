package txcom.gsi.kotlinmvp.data.mode

import android.os.Parcelable

/**
 * Created by Administrator on 2018/3/6.
 */
abstract class Base : Parcelable {

    abstract fun code(): Int
    abstract fun message(): String
}

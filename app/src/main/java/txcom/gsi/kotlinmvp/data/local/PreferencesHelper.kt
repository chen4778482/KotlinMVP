package txcom.gsi.kotlinmvp.data.local

import android.content.Context
import android.content.SharedPreferences
import txcom.gsi.kotlinmvp.injection.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Administrator on 2018/3/8.
 */
@Singleton
class PreferencesHelper {
    companion object {
        val PREF_FILE_NAME = "wisdomcommunity_pref_file"
        val KEY_PREFERENCES_VERSION = "key_preferences_version"
        val PREFERENCES_VERSION = 1
    }

    val mPref: SharedPreferences

    @Inject
    constructor(@ApplicationContext content: Context) {
        mPref = content.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        checkPreferences()
    }

    private fun checkPreferences() {
        val oldVersion = mPref.getInt(KEY_PREFERENCES_VERSION, 1)
        if (oldVersion < PREFERENCES_VERSION) {
            clear()
            val edit = mPref.edit()
            edit.putInt(KEY_PREFERENCES_VERSION, PREFERENCES_VERSION)
            edit.apply()
        }
    }

    fun clear() {
        mPref.edit().clear().apply()
    }

    fun delete(key: String) {
        if (mPref.contains(key)) {
            mPref.edit().remove(key).apply()
        }
    }

    fun savePref(key: String, value: Any?) {
        delete(key)
        if (value is Boolean) {
            mPref.edit().putBoolean(key, (value as Boolean?)!!).apply()
        } else if (value is Int) {
            mPref.edit().putInt(key, (value as Int?)!!).apply()
        } else if (value is Float) {
            mPref.edit().putFloat(key, (value as Float?)!!).apply()
        } else if (value is Long) {
            mPref.edit().putLong(key, (value as Long?)!!).apply()
        } else if (value is String) {
            mPref.edit().putString(key, value as String?).apply()
        } else if (value is Enum<*>) {
            mPref.edit().putString(key, value.toString()).apply()
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-primitive preference")
        }
    }

    fun <T> getPref(key: String): T {
        return mPref.all[key] as T
    }

    fun <T> getPref(key: String, defValue: T): T {
        val returnValue = mPref.all[key] as T
        return returnValue ?: defValue
    }
}
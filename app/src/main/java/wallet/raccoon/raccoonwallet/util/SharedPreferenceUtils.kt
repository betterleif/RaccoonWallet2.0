package wallet.raccoon.raccoonwallet.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import wallet.raccoon.raccoonwallet.BuildConfig
import wallet.raccoon.raccoonwallet.model.MyProfileEntity

class SharedPreferenceUtils(context: Context) {
    companion object {
        private const val SHARED_PREF_NAME = BuildConfig.APPLICATION_ID + ".preferences"

        private const val MY_PROFILE = "my_profile"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    var myProfile: MyProfileEntity
        get() = Gson().fromJson(sharedPreferences.getValue(MY_PROFILE), MyProfileEntity::class.java)
        set(value) {
            sharedPreferences.putValue(MY_PROFILE, Gson().toJson(value))
        }

    private fun SharedPreferences.getValue(key: String): String? = this.getString(key, "")

    private fun SharedPreferences.putValue(
        key: String,
        value: String
    ) = this.edit().putString(key, value).apply()

    private fun SharedPreferences.removeValue(key: String) = this.edit().remove(key).apply()
}
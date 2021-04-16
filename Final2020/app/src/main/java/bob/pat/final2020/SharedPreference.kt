package bob.pat.final2020

/*
 * Katelyn Patrick December 7th 2020
*/


import android.content.Context
import android.content.SharedPreferences


class SharedPreference(context: Context = TheApp.context) {

    // region Properties
    private val preferencesName = context.getString(R.string.app_name) // use the app name
    private val sharedPref: SharedPreferences = context.getSharedPreferences(preferencesName,
            Context.MODE_PRIVATE)
// endregion

// region Methods

    fun contains(KEY_NAME: String): Boolean {
        return sharedPref.contains(KEY_NAME)
    }

}

//endregion
package bob.pat.final2020

import android.app.Application
import android.content.Context

/*
* Katelyn Patrick December 7th 2020
*/

class TheApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
            private set
    }
}
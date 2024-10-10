package com.cyberbyte.mymovielibrary

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware

class MovieApp : Application(), DIAware {
    override val di = DI.lazy {
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize other things if needed
    }
}
package com.cyberbyte.mymovielibrary

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton

class MovieApp : Application(), DIAware {
    override val di = DI.lazy {
        bind<Application>() with singleton { this@MovieApp }
        import(appModule)
    }
}
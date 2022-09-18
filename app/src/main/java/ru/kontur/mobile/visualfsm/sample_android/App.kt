package ru.kontur.mobile.visualfsm.sample_android

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.kontur.mobile.visualfsm.sample_android.di.appModule
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appCode = UUID.randomUUID().toString()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            modules(appModule)
        }
    }

    companion object {
        lateinit var appCode: String
            private set
    }
}
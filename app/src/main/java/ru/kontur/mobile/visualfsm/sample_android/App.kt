package ru.kontur.mobile.visualfsm.sample_android

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.kontur.mobile.visualfsm.sample_android.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            modules(appModule)
        }
    }
}
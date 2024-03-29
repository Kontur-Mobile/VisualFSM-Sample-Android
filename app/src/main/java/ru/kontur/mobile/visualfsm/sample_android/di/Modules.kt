package ru.kontur.mobile.visualfsm.sample_android.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.interactor.AuthInteractor

val appModule = module {
    singleOf(::AuthInteractor)
}
package ru.kontur.mobile.visualfsm.sample_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.createActivityRetainedScope
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.component.getScopeName
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope

abstract class BaseActivity : ComponentActivity(), AndroidScopeComponent {
    override var scope: Scope? = null
    protected open val scopeModule: (Bundle?) -> Module? = { _ -> null }

    @OptIn(KoinInternalApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isScopeDefined = getKoin().scopeRegistry.scopeDefinitions.contains(getScopeName())
        createActivityRetainedScope()
        if (!isScopeDefined) {
            scopeModule(savedInstanceState)?.let {
                loadKoinModules(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations || isFinishing) {
            scope?.close()
            scope = null
            scopeModule(null)?.let {
                unloadKoinModules(it)
            }
        }
    }
}
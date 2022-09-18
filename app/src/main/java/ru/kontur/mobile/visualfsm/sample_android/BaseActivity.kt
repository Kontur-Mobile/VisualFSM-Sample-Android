package ru.kontur.mobile.visualfsm.sample_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getScopeId
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.core.scope.Scope

abstract class BaseActivity : ComponentActivity(), KoinScopeComponent {
    override val scope: Scope by lazy {
        // Scope id and qualifier use same string for load state based module
        getKoin().getOrCreateScope(stateScopeId, StringQualifier(stateScopeId))
    }

    protected open val stateScopeModule: (Qualifier, Bundle?) -> Module? = { _, _ -> null }

    private lateinit var stateScopeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateScopeId = savedInstanceState?.getString(STATE_SCOPE_ID) ?: getScopeId()

        if (isNewProcess(savedInstanceState)) {
            Log.d(this::class.simpleName, "Init new UI scope: ${scope.id}")
            stateScopeModule(scope.scopeQualifier, savedInstanceState)?.let {
                loadKoinModules(it)
            }
        } else {
            Log.d(this::class.simpleName, "Use exist UI scope: ${scope.id}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(APP_CODE, App.appCode)
        outState.putString(STATE_SCOPE_ID, scope.id)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations || isFinishing) {
            closeScopeAndRemoveStateModule()
        }
    }

    private fun closeScopeAndRemoveStateModule() {
        Log.d(this::class.simpleName, "Destroy UI scope: ${scope.id}")
        scope.close()
        stateScopeModule(scope.scopeQualifier, null)?.let { module ->
            unloadKoinModules(module)
        }
    }

    private fun isNewProcess(savedInstanceState: Bundle?): Boolean {
        val savedAppCode = savedInstanceState?.getString(APP_CODE)
        return savedAppCode != App.appCode
    }

    companion object {
        private const val APP_CODE = "app_code"
        private const val STATE_SCOPE_ID = "state_scope_id"
    }
}
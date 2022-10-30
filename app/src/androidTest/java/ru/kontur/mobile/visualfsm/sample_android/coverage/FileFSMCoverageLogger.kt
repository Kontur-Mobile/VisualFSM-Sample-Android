package ru.kontur.mobile.visualfsm.sample_android.coverage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import ru.kontur.mobile.visualfsm.Action
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.State
import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.common.fsm.coverage.FSMCoverageLogger
import java.io.File
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class FileFSMCoverageLogger : FSMCoverageLogger() {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    @Suppress("UNCHECKED_CAST")
    override fun <STATE : State> logTransition(
        baseStateClass: KClass<STATE>,
        actionClass: KClass<out Action<STATE>>,
        transitionClass: KClass<out Transition<STATE, STATE>>,
        useTransitionName: Boolean,
    ) {
        val directory = provide(File("ui_fsm_coverage"))

        val file = directory.resolve("${baseStateClass.simpleName}CoveredTransitions.txt")
        if (!file.exists()) {
            file.createNewFile()
        }

        val nameFromEdgeAnnotation = transitionClass.findAnnotation<Edge>()?.name

        val edgeName = when {
            nameFromEdgeAnnotation != null -> nameFromEdgeAnnotation
            useTransitionName -> transitionClass.simpleName
            else -> actionClass.simpleName
        } ?: throw IllegalStateException("Edge must have name")

        val fromState = transitionClass.supertypes.first().arguments
            .first().type?.classifier as KClass<out State>
        val toState = transitionClass.supertypes.first().arguments
            .last().type?.classifier as KClass<out State>

        val fromStateName = fromState.getSimpleNameWithSealedName(baseStateClass)
        val toStateName = toState.getSimpleNameWithSealedName(baseStateClass)
        val textToWrite = "${edgeName},$fromStateName,$toStateName"

        writeLineIfNotExists(file, textToWrite)
    }

    private fun provide(dest: File): File {
        return provideNew(dest)
    }

    @Suppress("DEPRECATION")
    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    private fun provideNew(dest: File): File {
        val dir: File = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> instrumentation.targetContext.applicationContext.filesDir.resolve(dest)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> Environment.getExternalStorageDirectory().resolve(dest)
            else -> instrumentation.targetContext.applicationContext.getDir(dest.canonicalPath, Context.MODE_WORLD_READABLE)
        }
        return dir.createDirIfNeeded()
    }

    /**
     * Creates a directory if it does not exist with all needed parent dirs, then grants RWX permissions.
     */
    private fun File.createDirIfNeeded(): File = apply {
        if (!exists()) {
            mkdirs()
            grantRwxPermissions()
        }
    }

    private fun File.grantRwxPermissions(): File = apply {
        setReadable(true, false)
        setWritable(true, false)
        setExecutable(true, false)
    }

    private fun writeLineIfNotExists(file: File, line: String) {
        if (!file.readLines().contains(line)) {
            file.appendText(line + "\n")
        }
    }

    private fun <STATE : State> KClass<out STATE>.getSimpleNameWithSealedName(baseStateClass: KClass<out STATE>): String {
        return this.qualifiedName!!.substringAfterLast("${baseStateClass.simpleName}.")
    }
}
package ru.kontur.mobile.visualfsm.sample_android.coverage

import androidx.test.platform.app.InstrumentationRegistry
import ru.kontur.mobile.visualfsm.Action
import ru.kontur.mobile.visualfsm.State
import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.common.fsm.coverage.FSMCoverageLogger
import ru.kontur.mobile.visualfsm.tools.VisualFSM
import java.io.File
import kotlin.reflect.KClass

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

        val edgeName = VisualFSM.getEdgeName(transitionClass)

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

    private fun provideNew(dest: File): File {
        val dir = instrumentation.targetContext.applicationContext.filesDir.resolve(dest)
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
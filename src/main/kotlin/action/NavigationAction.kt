package action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys.VIRTUAL_FILE

class NavigationAction(private val navigator: Navigator) {
    fun goToTestOrImplementation(e: AnActionEvent) {
        val virtualFile = e.getData(VIRTUAL_FILE)

        if (virtualFile?.nameWithoutExtension?.contains("test")!!) {
            navigator.navigateTo(e.project!!, "aTestFile.tsx")
        } else {
            navigator.navigateTo(e.project!!, "anImplementation.test.js")
        }

    }
}

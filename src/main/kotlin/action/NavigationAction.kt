package action

import action.intellij.Navigator
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys.VIRTUAL_FILE

class NavigationAction(private val navigator: Navigator, val fileNameService: FileNameService) {
    fun goToTestOrImplementation(e: AnActionEvent) {
        val virtualFile = e.getData(VIRTUAL_FILE)
        val nameWithoutExtension = virtualFile?.nameWithoutExtension

        if (nameWithoutExtension?.contains(".test")!!) {
            val implementationFileWithExtension =
                fileNameService.toImplementationFileNameWithExtension(e.project!!, nameWithoutExtension)
            navigator.navigateTo(e.project!!, implementationFileWithExtension)
        } else {
            val fileNameWithExtension = fileNameService.toTestFileNameWithExtension(nameWithoutExtension)
            navigator.navigateTo(e.project!!, fileNameWithExtension)
        }

    }
}

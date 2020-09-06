package action

import action.intellij.IntellijService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys.VIRTUAL_FILE

class NavigationAction(private val intellijService: IntellijService, val fileNameService: FileNameService) {
    fun goToTestOrImplementation(e: AnActionEvent) {
        val virtualFile = e.getData(VIRTUAL_FILE)
        val nameWithoutExtension = virtualFile?.nameWithoutExtension

        if (nameWithoutExtension?.contains(".test")!!) {
            val implementationFileWithExtension =
                fileNameService.toImplementationFileNameWithExtension(e.project!!, nameWithoutExtension)
            intellijService.navigateTo(e.project!!, implementationFileWithExtension)
        } else {
            val fileNameWithExtension = fileNameService.toTestFileNameWithExtension(nameWithoutExtension)
            intellijService.navigateTo(e.project!!, fileNameWithExtension)
        }

    }
}

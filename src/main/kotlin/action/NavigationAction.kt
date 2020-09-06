package action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys.VIRTUAL_FILE
import org.jetbrains.annotations.NotNull

class NavigationAction(private val navigator: Navigator, val fileNameService: FileNameService) {
    fun goToTestOrImplementation(e: AnActionEvent) {
        val virtualFile = e.getData(VIRTUAL_FILE)
        val nameWithoutExtension = virtualFile?.nameWithoutExtension

        if (nameWithoutExtension?.contains(".test")!!) {
            val implementationFileWithExtension =
                fileNameService.toImplementationFileNameWithExtension(nameWithoutExtension)
            navigator.navigateTo(e.project!!, implementationFileWithExtension)
        } else {
            val fileNameWithExtension = fileNameService.toTestFileNameWithExtension(nameWithoutExtension)
            navigator.navigateTo(e.project!!, fileNameWithExtension)
        }

    }

    private fun testFileNameToImplementationFileNameWithoutExtension(nameWithoutExtension: @NotNull String?): String {
        return "aTestFile.tsx"
    }
}

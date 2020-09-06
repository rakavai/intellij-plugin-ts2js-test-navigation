package action

import action.intellij.IntellijService
import com.intellij.openapi.project.Project

class FileNameService(private val intellijService: IntellijService) {
    fun toImplementationFileNameWithExtension(project: Project, nameWithoutExtension: String): String {
        val (onlyName) = Regex("(.+)\\.test").find(nameWithoutExtension)!!.destructured
        val tsxFileName = "$onlyName.tsx"
        if (intellijService.getFirstVirtualFile(project, tsxFileName) != null) {
            return tsxFileName
        }
        return "$onlyName.ts"
    }

    fun toTestFileNameWithExtension(nameWithoutExtension: String): String {
        return "$nameWithoutExtension.test.js"
    }
}

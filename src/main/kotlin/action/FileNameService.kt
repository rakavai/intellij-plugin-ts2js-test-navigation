package action

import action.intellij.IntellijService
import com.intellij.openapi.project.Project

class FileNameService(intellijService: IntellijService) {
    fun toImplementationFileNameWithExtension(project: Project, nameWithoutExtension: String): String {
        val (onlyName) = Regex("(.+)\\.test").find(nameWithoutExtension)!!.destructured
        return "$onlyName.tsx"
    }

    fun toTestFileNameWithExtension(nameWithoutExtension: String): String {
        return "$nameWithoutExtension.test.js"
    }
}

package action.intellij

import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.search.FilenameIndex.getVirtualFilesByName
import com.intellij.psi.search.GlobalSearchScope.projectScope

class IntellijService {
    fun navigateTo(project: Project, fileName: String) {
        val virtualFilesByName = getVirtualFilesByName(project, fileName, projectScope(project))
        val firstVirtualFile = virtualFilesByName.iterator().next()
        val descriptor = OpenFileDescriptor(project, firstVirtualFile)
        descriptor.navigate(true)
    }
}

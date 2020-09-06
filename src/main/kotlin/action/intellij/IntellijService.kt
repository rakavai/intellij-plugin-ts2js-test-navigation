package action.intellij

import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.FilenameIndex.getVirtualFilesByName
import com.intellij.psi.search.GlobalSearchScope.projectScope

class IntellijService {
    fun navigateTo(project: Project, fileName: String) {
        val firstVirtualFile = getFirstVirtualFile(project, fileName)
        navigateTo(project, firstVirtualFile)
    }

    private fun navigateTo(project: Project, firstVirtualFile: VirtualFile?) {
        val descriptor = OpenFileDescriptor(project, firstVirtualFile!!)
        descriptor.navigate(true)
    }

    public fun getFirstVirtualFile(project: Project, fileName: String): VirtualFile? {
        val virtualFilesByName =
            getVirtualFilesByName(project, fileName, projectScope(project))
        return virtualFilesByName.iterator().next()
    }
}

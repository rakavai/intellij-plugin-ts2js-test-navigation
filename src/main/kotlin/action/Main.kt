package action

import action.intellij.IntellijService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class Main: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        Action.goToTestOrImplementation(e)
    }
}

private object Action {
    val intellijService = IntellijService()
    val navigationAction = NavigationAction(intellijService, FileNameService())

    fun goToTestOrImplementation(e: AnActionEvent) {
        navigationAction.goToTestOrImplementation(e)
    }
}

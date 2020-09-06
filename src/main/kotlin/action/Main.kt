package action

import action.intellij.Navigator
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class Main: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        Action.goToTestOrImplementation(e)
    }
}

private object Action {
    val navigator = Navigator()
    val navigationAction = NavigationAction(navigator, FileNameService())

    fun goToTestOrImplementation(e: AnActionEvent) {
        navigationAction.goToTestOrImplementation(e)
    }
}

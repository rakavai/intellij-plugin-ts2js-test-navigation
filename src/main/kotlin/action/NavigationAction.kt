package action

import com.intellij.openapi.actionSystem.AnActionEvent

class NavigationAction(private val navigator: Navigator) {
    fun goToTestOrImplementation(e: AnActionEvent) {
        navigator.navigateTo(e.project!!, "anImplementation.test.js")
    }
}

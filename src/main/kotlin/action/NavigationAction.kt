package action

import com.intellij.openapi.actionSystem.AnActionEvent

class NavigationAction(private val navigator: Navigator) {
    fun goToTestOrImplementation(e: AnActionEvent) {
        navigator.navigateTo("anImplementation.test.js")
    }
}

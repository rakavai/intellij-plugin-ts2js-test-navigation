package action

import com.intellij.mock.MockVirtualFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NavigationActionTest {
    @Nested
    inner class TestGoToTestOrImplementation {
        @Nested
        inner class WhenTheCurrentFileIsAImplementationFile {
            @Test
            fun `it opens the related test file for the file`() {
                val e = mockk<AnActionEvent>()
                every {
                    e.getData(PlatformDataKeys.VIRTUAL_FILE)
                } returns MockVirtualFile("anImplementation.tsx")

                val navigator = spyk<Navigator>()

                val testNavigationAction = NavigationAction(navigator)
                testNavigationAction.goToTestOrImplementation(e)

                verify { navigator.navigateTo("anImplementation.test.js") }
            }
        }
    }
}

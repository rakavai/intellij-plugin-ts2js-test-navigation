package action

import action.intellij.IntellijService
import com.intellij.mock.MockProjectEx
import com.intellij.mock.MockVirtualFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import io.mockk.every
import io.mockk.mockk
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
                val navigator = mockk<IntellijService>(relaxed = true)
                val testNavigationAction = NavigationAction(navigator, FileNameService(IntellijService()))
                val mockProjectEx = MockProjectEx { }

                val e = mockActionEvent("anImplementation.tsx", mockProjectEx)
                testNavigationAction.goToTestOrImplementation(e)

                verify { navigator.navigateTo(mockProjectEx, "anImplementation.test.js") }
            }
        }

        @Nested
        inner class WhenTheCurrentFileIsATestFile {
            @Test
            fun `it opens the related implementation file for the test`() {
                val mockProjectEx = MockProjectEx { }

                val fileNameService = mockk<FileNameService>()
                every {
                    fileNameService.toImplementationFileNameWithExtension(mockProjectEx,"aTestFile.test")
                } returns "aTestFile.tsx"

                val navigator = mockk<IntellijService>(relaxed = true)

                val testNavigationAction = NavigationAction(navigator, fileNameService)
                testNavigationAction.goToTestOrImplementation(
                    mockActionEvent("aTestFile.test.js", mockProjectEx)
                )

                verify { navigator.navigateTo(mockProjectEx, "aTestFile.tsx") }
            }
        }
    }

    private fun mockActionEvent(fileName: String, mockProjectEx: MockProjectEx): AnActionEvent {
        val e = mockk<AnActionEvent>()
        every {
            e.getData(PlatformDataKeys.VIRTUAL_FILE)
        } returns MockVirtualFile(fileName)
        every { e.project } returns mockProjectEx
        return e
    }
}

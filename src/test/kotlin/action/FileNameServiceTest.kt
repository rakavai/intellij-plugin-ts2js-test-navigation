package action

import action.intellij.IntellijService
import com.intellij.mock.MockProjectEx
import com.intellij.mock.MockVirtualFile
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class FileNameServiceTest {
    @Nested
    inner class TestToImplementationFileNameWithExtension {
        @Nested
        inner class WhenThreeIsATSXFileForTestFile {
            @Test
            fun `it change test file without extension to tsx implementation file`() {
                val stubbedProject = MockProjectEx {}

                val intellijService = mockk<IntellijService>()
                every {
                    intellijService.getFirstVirtualFile(stubbedProject, "AFile.tsx")
                } returns MockVirtualFile("AFile.tsx")

                val fileNameService = FileNameService(intellijService)
                val actual =
                    fileNameService.toImplementationFileNameWithExtension(stubbedProject, "AFile.test")

                assertEquals("AFile.tsx", actual)
            }
        }

        @Nested
        inner class WhenThreeIsNoTSXFileForTestFile {
            @Test
            fun `it change test file without extension to TS implementation file`() {
                val stubbedProject = MockProjectEx {}

                val intellijService = mockk<IntellijService>()
                every {
                    intellijService.getFirstVirtualFile(stubbedProject, "AFile.tsx")
                } returns null

                val fileNameService = FileNameService(intellijService)
                val actual =
                    fileNameService.toImplementationFileNameWithExtension(stubbedProject, "AFile.test")

                assertEquals("AFile.ts", actual)
            }
        }
    }

    @Nested
    inner class TestToTestFileNameWithExtension {
        @Test
        fun `it change implementation file without extension to test file`() {
            val fileNameService = FileNameService(IntellijService())
            val actual =
                fileNameService.toTestFileNameWithExtension("AFile")

            assertEquals("AFile.test.js", actual)
        }
    }
}

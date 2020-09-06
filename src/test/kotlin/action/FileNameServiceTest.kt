package action

import com.intellij.mock.MockProjectEx
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class FileNameServiceTest {
    @Nested
    inner class TestToImplementationFileNameWithExtension {
        @Test
        fun `it change test file without extension to tsx implementation file`() {
            val fileNameService = FileNameService()
            val actual =
                fileNameService.toImplementationFileNameWithExtension(MockProjectEx{}, "AFile.test")

            assertEquals("AFile.tsx", actual)
        }
    }

    @Nested
    inner class TestToTestFileNameWithExtension {
        @Test
        fun `it change implementation file without extension to test file`() {
            val fileNameService = FileNameService()
            val actual =
                fileNameService.toTestFileNameWithExtension("AFile")

            assertEquals("AFile.test.js", actual)
        }
    }
}

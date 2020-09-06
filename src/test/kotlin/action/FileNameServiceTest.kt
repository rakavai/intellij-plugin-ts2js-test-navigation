package action

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class FileNameServiceTest{
    @Nested
    inner class TestToImplementationFileNameWithExtension{
        @Test
        fun `it change test file without extension to tsx implementation file`(){
            val fileNameService = FileNameService()
            val actual =
                fileNameService.toImplementationFileNameWithExtension("AFile.test")

            assertEquals("AFile.tsx", actual)
        }
    }
}

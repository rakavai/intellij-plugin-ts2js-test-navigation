package action

import org.jetbrains.annotations.NotNull

class FileNameService {
    fun toImplementationFileNameWithExtension(nameWithoutExtension: @NotNull String?): String {
        return "aTestFile.tsx"
    }

    fun toTestFileNameWithExtension(nameWithoutExtension: String): String {
        return "anImplementation.test.js"
    }
}

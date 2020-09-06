package action

class FileNameService {
    fun toImplementationFileNameWithExtension(nameWithoutExtension: String): String {
        val (onlyName) = Regex("(.+)\\.test").find(nameWithoutExtension)!!.destructured
        return "$onlyName.tsx"
    }

    fun toTestFileNameWithExtension(nameWithoutExtension: String): String {
        return "$nameWithoutExtension.test.js"
    }
}

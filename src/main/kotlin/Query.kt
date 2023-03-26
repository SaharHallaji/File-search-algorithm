import exceptions.ExtensionNotValidException
import java.io.File
import java.io.FileNotFoundException

class Query {
    data class Builder(
        private var animal: String? = null,
        private var file: File? = null
    ) {
        fun setFile(file: File) = apply { this.file = file }
        fun setAnimal(animal: String) = apply { this.animal = animal }
        fun search(): MutableList<String> {
            if (file == null || !file!!.exists() || !file!!.isFile ) throw FileNotFoundException()
            if (file!!.extension != "txt") throw ExtensionNotValidException()
            val file = this.file!!
            val animals = mutableListOf<String>()
            file.forEachLine {
                if (Regex(animal!!, RegexOption.IGNORE_CASE).containsMatchIn(it)) animals.add(it)
            }
            return animals
        }
    }
}
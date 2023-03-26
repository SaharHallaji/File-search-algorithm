import exceptions.ExtensionNotValidException
import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

fun main() {
    var animalInput: String?
    while (true) {
        println(">> Please Enter a Name! < Press 0 For Exit> ")
        animalInput = readlnOrNull()

        if (animalInput == "0") exitProcess(0)
        if (animalInput.isNullOrEmpty()) println("Animal's Name")
        else {
            try {
                val query = Query.Builder()
                    .setFile(File(object {}.javaClass.getResource("animals.txt")!!.file))
                    .setAnimal(animalInput)
                    .search()
                println("found ${query.size} animals")
                query.forEach {
                    print("$it - ")
                }
                println()
            } catch (e: FileNotFoundException) {
                println(":( File Not Found!")
            } catch (e: ExtensionNotValidException) {
                println(":( Extension Is Not Valid!")
            } catch (e: NullPointerException) {
                println(":( File Address Is Null!")
            }
        }


    }
}
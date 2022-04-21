package cryptography

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    var inputFileName: String? = null
    var outputFileName: String? = null
    println("Task (hide, show, exit): ")
    while (true) {
        when (readLine()) {
            "hide" -> {
                println("Input image file: ")
                inputFileName = readLine()
                println("Output image file: ")
                outputFileName = readLine()
                println("Input Image: ${inputFileName?.replace('/', '\\', false)}")
                println("Output Image: ${outputFileName?.replace('/', '\\', false)}")
                writeFile(inputFileName ?: "defaultInput", outputFileName ?: "defaultOutput")
            }
            "show" -> println("Obtaining message from image.")
            "exit" -> {
                println("Bye!")
                return
            }
            else -> println("Invalid task.")
        }
    }
}

fun writeFile(inputFileName: String, outputFileName: String) {
    try {
        val inputFile = ImageIO.read(File(inputFileName))
        for (i in 0 until inputFile.width) {
            for (j in 0 until inputFile.height) {
                val inputColor = Color(inputFile.getRGB(i, j))
                val outputRgb = Color(
                    inputColor.red or 1, // bitwise or, set least significant bit to 1
                    inputColor.green or 1,
                    inputColor.blue or 1
                ).rgb
                inputFile.setRGB(i, j, outputRgb)
            }
        }
        ImageIO.write(inputFile, "png", File(outputFileName))
        println("Image ${outputFileName.replace('/', '\\', false)} is saved.") // Trying to pass tests using different file system
    } catch (e: Exception) {
        println("Can't read input file!")
    }
}

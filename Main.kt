import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    var inputFileName:String? = null
    var outputFileName:String? = null
    print("Task (hide, show, exit): ")
    while (true) {
        when(readLine()) {
            "hide" -> {
                println("Hiding message in image.")
                print("Input image file: ")
                inputFileName = readLine()
                print("Output image file: ")
                outputFileName = readLine()
                println("Input Image: $inputFileName")
                println("Output Image: $outputFileName")
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

//    println("Hello World!")
//    try {
//        val image: BufferedImage = drawSquare() // retrieve image
//        val outputFile = File("square.png")
//        ImageIO.write(image, "png", outputFile)
//        println("Saved image ${outputFile.absolutePath}")
//    } catch (e: IOException) {
//        println("Error saving image")
//    }
}

fun drawLines(): BufferedImage {
    val myImage = BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB)
    val graphics = myImage.createGraphics()
    graphics.color = Color.RED
    graphics.drawLine(0, 0, 200, 200)
    graphics.color = Color.GREEN
    graphics.drawLine(0, 200, 200, 0)
    return myImage
}

fun drawStrings(): BufferedImage {
    val helloImages = "Hello, images!"
    val myImage = BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB)
    val graphics = myImage.createGraphics()
    graphics.color = Color.RED
    graphics.drawString(helloImages, 50, 50)
    graphics.color = Color.GREEN
    graphics.drawString(helloImages, 51, 51)
    graphics.color = Color.BLUE
    graphics.drawString(helloImages, 52, 52)
    return myImage
}

fun drawSquare(): BufferedImage {
    val myImage = BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
    val graphics = myImage.createGraphics()
    graphics.color = Color.RED
    graphics.drawRect(100, 100, 300, 300)
    return myImage
}

fun setLeastSignificantBitToOne(pixel : Int): Int {
    return if (pixel % 2 == 0) pixel + 1 else pixel
}

fun writeFile(inputFileName: String, outputFileName: String) {
    try {
        val inputFile = ImageIO.read(File(inputFileName))
        for (i in 0 until inputFile.width) {
            for (j in 0 until inputFile.height) {
                val color = Color(inputFile.getRGB(i, j))
                val rgb = Color(
                    setLeastSignificantBitToOne(color.red),
                    setLeastSignificantBitToOne(color.green),
                    setLeastSignificantBitToOne(color.blue)
                ).rgb
                inputFile.setRGB(i, j, rgb)
            }
        }
        ImageIO.write(inputFile, "png", File(outputFileName))
        println("image created")
    } catch(e: Exception) {
        println("Error reading/writing file. ${e.message}")
    }
}

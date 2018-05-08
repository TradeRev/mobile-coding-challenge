package com.example.joao.photoscodechallenge

import java.io.File
import java.util.*

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class FileHandler {

    operator fun invoke(fileName: String): String {

        val result = StringBuilder()
        val classLoader = FileHandler::class.java.classLoader
        val file = File(classLoader.getResource(fileName).file)

        try {
            val scanner = Scanner(file)
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                result.append(line).append("\n")
            }

            scanner.close()
            return result.toString()

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        throw RuntimeException("Cannot read file $fileName")
    }

}
package com.example.joao.photoscodechallenge

import android.content.Context
import java.util.*

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class FileHandler {

    fun readResource(context: Context, fileName: String): String{

        val stream = context.resources.assets.open(fileName)
        val sb = StringBuilder()

        try {
            val scanner = Scanner(stream)
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                sb.append(line).append("\n")
            }
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            stream.close()
        }
        return sb.toString()
    }

}
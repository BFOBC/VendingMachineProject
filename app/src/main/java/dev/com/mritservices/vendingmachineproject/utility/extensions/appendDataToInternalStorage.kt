package dev.com.mritservices.vendingmachineproject.utility.extensions

import android.os.Environment
import android.os.Environment.DIRECTORY_DOCUMENTS
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun appendDataToInternalStorage(filename: String, data: String) {
    val directory = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS).path + "/vendingLogs/"
    val file = File(directory)
    if (!file.exists()) {
        file.mkdirs()
    }
    // Create or open the file for appending
    val mypath = File(directory, filename)
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(mypath, true) // Open the file in append mode
        fos.write(data.toByteArray()) // Write the data to the file
        fos.write("\n".toByteArray()) // Write a newline character to separate entries
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
package com.example.weather.utils

import android.os.Environment
import com.example.weather.BuildConfig
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class FileLoggingTree : Timber.Tree() {

    val LOG_TAG = FileLoggingTree::class.simpleName
    var logFile: File? = null

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val path = "LOGUS"
        val fileFormat = ".txt"
        val fileNameTimeStamp = SimpleDateFormat("hh-mm-ss-SSS", Locale.getDefault()).format(Date())
        val fileName = "$fileNameTimeStamp$fileFormat"

        logFile = createFile(path, fileName)
        writeLogsInFile(tag, message)
    }

    private fun createFile(path: String, fileName: String): File {
        var file: File? = null
        if (isExternalStorageAvailable()) {
            val root = File(
                Environment.getExternalStorageDirectory().getAbsolutePath(),
                BuildConfig.APPLICATION_ID + File.separator + path
            )
            var dirExists = true
            if (!root.exists()) {
                dirExists = root.mkdirs()
            }
            if (dirExists) {
                file = File(root, fileName)
            }
        }
        return file!!
    }

    private fun writeLogsInFile(tag: String?, message: String) {
        val logTimeStamp = SimpleDateFormat(
            "E MMM dd yyyy 'at' hh:mm:ss:SSS aaa ",
            Locale.getDefault()
        ).format(Date())

        try {
            if (!logFile!!.exists()) {
                val fileWriter = FileWriter(logFile!!, true)
                fileWriter.apply {
                    append(logTimeStamp)
                    append(tag)
                    append("\n")
                    append(message)
                    flush()
                    close()
                }
            }
        } catch (e: Exception) {
            Timber.e(LOG_TAG, "Error while logging into file : $e")
        }
    }

    private fun isExternalStorageAvailable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }

    fun saveLogCat() {
        try {
            val file = File("${Environment.getExternalStorageDirectory()}/app-logger.txt")
            if (!file.exists()) {
                file.createNewFile()
            }

            Runtime.getRuntime().exec("logcat -v time -f " + file.absolutePath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
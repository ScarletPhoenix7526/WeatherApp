package com.example.weather.utils

import android.content.Context
import com.example.weather.BuildConfig
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class FileLoggingTree(val context: Context) : Timber.Tree() {

    val LOG_TAG = FileLoggingTree::class.simpleName
    var logFile: File? = null

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val path = "Log"
        val fileFormat = ".txt"
        val fileNameTimeStamp = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val fileName = "$fileNameTimeStamp$fileFormat"

        logFile = createFile(path, fileName)

    }

    fun createFile(path: String, fileName: String): File {
        var file: File? = null
        var rootDirIsExists = true
        val rootFile = File(context.filesDir, "${BuildConfig.APPLICATION_ID}${File.separator}$path" )

        if (rootFile.exists().not()) {
            rootDirIsExists = rootFile.mkdir()
        }

        if (rootDirIsExists) {
            file = File(rootFile, fileName)
        }

        return file!!
    }

    fun writeLogsInFile(tag: String, message: String) {
        val logTimeStamp = SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa", Locale.getDefault()).format(Date())

        try {
            if (!logFile!!.exists()) {
                val fileWriter = FileWriter(logFile!!, true)
                fileWriter.apply {
                    append(logTimeStamp)
                    append(tag)
                    append(" - ")
                    append(message)
                    flush()
                    close()
                }
            }
        } catch (e: Exception) {
            Timber.e(LOG_TAG, "Error while logging into file : $e" )
        }
    }
}
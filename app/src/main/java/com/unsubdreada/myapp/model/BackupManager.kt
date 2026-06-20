package com.unsubdreada.myapp.model

import android.content.Context
import android.os.Environment
import com.unsubdreada.myapp.data.AppDatabase
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object BackupManager {
    private const val DB_NAME = "finance_db"

    private fun copyFile(src: File, dst: File) {
        if (!src.exists()) return
        FileInputStream(src).channel.use { sourceChannel ->
            FileOutputStream(dst).channel.use { destChannel ->
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size())
            }
        }
    }

    private fun createBackupFolder(): File {
        val downloadsFolder =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val backupFolder = File(downloadsFolder, "BPlannerBackups")

        if (!backupFolder.exists()) {
            backupFolder.mkdirs()
        }
        return backupFolder
    }

    fun exportDatabase(context: Context): Boolean {
        return try {
            val targetFolder = createBackupFolder()
            val suffixes = listOf("", "-wal", "-shm")

            suffixes.forEach { suffix ->
                val currentFile = context.getDatabasePath("$DB_NAME$suffix")
                val backupFile = File(targetFolder, "bplanner_backup.db$suffix")
                copyFile(currentFile, backupFile)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun importDatabase(context: Context): Boolean {
        return try {
            val targetFolder = createBackupFolder()
            val mainBackupFile = File(targetFolder, "bplanner_backup.db")

            if (!mainBackupFile.exists()) return false

            AppDatabase.getDatabase(context).close()

            val suffixes = listOf("", "-wal", "-shm")

            suffixes.forEach { suffix ->
                val currentFile = context.getDatabasePath("$DB_NAME$suffix")
                val backupFile = File(targetFolder, "bplanner_backup.db$suffix")

                if (currentFile.exists()) currentFile.delete()

                copyFile(backupFile, currentFile)
            }
            AppDatabase.resetInstance()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
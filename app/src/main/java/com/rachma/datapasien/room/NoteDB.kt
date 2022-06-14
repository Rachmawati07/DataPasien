package com.rachma.datapasien.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Untuk menjadikan data class Note yang akan digunakan sebagai database, dan versi databasenya adalah 1
@Database(
    entities = [Note::class],
    version = 1
)

// Untuk membuat abstract class NoteDB yang akan mewarisi fungsi dari class RoomDatabase
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var instance: NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        // Untuk membuat fungsi buildDatabase untuk membuat database note12345.db sebagai tempat penyimpanan data
        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            NoteDB::class.java,
            "note12345.db"
        ).build()
    }
}
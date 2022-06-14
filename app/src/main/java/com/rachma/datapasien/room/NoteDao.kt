package com.rachma.datapasien.room

import androidx.room.*

// Untuk menjadikan interface NoteDao sebagai DAO
@Dao

// Untuk mendeklarasikan interface yang bernama NoteDao
interface NoteDao {
    // Untuk menambahkan anotation @Insert untuk menambah data, lalu membuat fungsi addNote()
    @Insert
    suspend fun addNote(note: Note)

    // Untuk menambahkan anotation @Update untuk mengubah data, lalu membuat fungsi updateNote()
    @Update
    suspend fun updateNote(note: Note)

    // Untuk menambahkan anotation @Delete untuk menambah data, lalu membuat fungsi deleteNote()
    @Delete
    suspend fun deleteNote(note: Note)

    // Untuk menampilkan data dari tabel note, lalu membuat fungsi getNotes() yang berisi data dari kelas Note
    @Query("SELECT * FROM note")
    suspend fun getNotes(): List<Note>

    // Untuk menampilkan data dengan id tertentu, lalu membuat fungsi getNotes() dengan parameter note_id untuk mengambil id data
    @Query("SELECT * FROM note WHERE id=:note_id")
    suspend fun getNote(note_id: Int): List<Note>
}
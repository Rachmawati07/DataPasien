package com.rachma.datapasien.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// Untuk menjadikan data class Note sebagai entity
@Entity

// Membuat class yang bernama Note
data class Note(
    // Untuk menjadikan id sebagai primary key, kemudian nilai id akan di generate secara otomatis
    @PrimaryKey(autoGenerate = true)
    // Untuk mendeklarasikan entity atau kolom yang digunakan pada tabel, yang terdapat kolom untuk id, title, nama, ttl, alamat, jk, dan riwayat
    val id: Int,
    val title: String,
    val nama: String,
    val ttl: String,
    val alamat: String,
    val jk: String,
    val riwayat: String
)

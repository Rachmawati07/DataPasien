package com.rachma.datapasien.room

// Untuk mendeklarasikan class yang bernama Constant
class Constant {
    companion object {
        // Untuk menyimpan id 0 ke dalam TYPE_READ untuk membaca data
        const val TYPE_READ = 0

        // Untuk menyimpan id 1 ke dalam TYPE_CREATE untuk menambah data
        const val TYPE_CREATE = 1

        // Untuk menyimpan id 2 ke dalam TYPE_UPDATE untuk mengupdate data
        const val TYPE_UPDATE = 2
    }
}
package com.rachma.datapasien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rachma.datapasien.databinding.ActivityInformasiBinding

// Untuk mendeklarasikan kelas yang bernama InformasiActivity
// Dan untuk menginisialisasi variabel binding untuk ActivityInformasiBinding
class InformasiActivity : AppCompatActivity(){
    private lateinit var binding: ActivityInformasiBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi)

        // Untuk memanggil ActivityInformasi Binding dengan menggunakan binding
        binding = ActivityInformasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk memberikan fungsi klik listener pada buttonHomeAbout agar ketika diklik bisa diarahkan pada fungsi home
        binding.buttonHome.setOnClickListener { home() }
    }

    // Untuk mendeklarasikan fungsi yang bernama home
    private fun home(){

        // Untuk mendeklarasikan variabel berupa intentHome dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(InformasiActivity menuju ke kelas HomeActivity)
        val intentHome = Intent(this, HomeActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentHome)
    }
}
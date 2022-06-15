package com.rachma.datapasien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rachma.datapasien.databinding.ActivityHomeBinding
import com.rachma.datapasien.room.Constant

// Untuk mendeklarasikan kelas yang bernama HomeActivity
// Dan untuk menginisialisasi variabel binding untuk ActivityHomeBinding
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Untuk memanggil ActivityHomeBinding dengan menggunakan binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk memberikan fungsi klik listener pada buttonInfor agar ketika diklik bisa diarahkan pada fungsi informasi
        binding.buttonInfor.setOnClickListener { informasi() }

        // Untuk memberikan fungsi klik listener pada buttonDaftarPasien agar ketika diklik bisa diarahkan pada fungsi daftarPasien
        binding.buttonDaftarPasien.setOnClickListener { daftarPasien() }

        // Untuk memberikan fungsi klik listener pada buttonProfile agar ketika diklik bisa diarahkan pada fungsi profile
        binding.buttonProfile.setOnClickListener { profile() }

        // Untuk membuat fungsi yang bernama setupListener
        setupListener()
    }

    // Untuk mendeklarasikan fungsi yang bernama informasi
    private fun informasi(){

        // Untuk mendeklarasikan variabel berupa intentInformasi dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas InformasiActivity)
        val intentInformasi = Intent(this, InformasiActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentInformasi)
    }

    // Untuk mendeklarasikan fungsi yang bernama setupListener
    private fun setupListener() {

        // Untuk memberikan fungsi klik listener pada buttonTambahData agar ketika diklik bisa menjalankan fungsi intentEdit
        binding.buttonTambahData.setOnClickListener {
            //Memulai activity
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama intentEdit
    fun intentEdit(noteId: Int, intentType: Int){
        // Untuk menjalankan activity melalui intent dari HomeActivity menuju ke TambahDataActivity
        startActivity(
            Intent(applicationContext, TambahDataActivity::class.java)
                .putExtra("intent_id", noteId)
                .putExtra("intent_type", intentType)
        )
    }

    // Untuk mendeklarasikan fungsi yang bernama daftarPasien
    private fun daftarPasien(){

        // Untuk mendeklarasikan variabel berupa intentDaftar dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas daftarPasienActivity)
        val intentDaftar = Intent(this, DaftarPasienActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentDaftar)
    }

    // Untuk mendeklarasikan fungsi yang bernama profile
    private fun profile(){

        // Untuk mendeklarasikan variabel berupa intentProfile dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas ProfileActivity)
        val intentProfile = Intent(this, ProfileActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentProfile)
    }
}
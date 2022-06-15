package com.rachma.datapasien

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rachma.datapasien.databinding.ActivityTambahDataBinding
import com.rachma.datapasien.room.Constant
import com.rachma.datapasien.room.Note
import com.rachma.datapasien.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.view.View

// Untuk mendeklarasikan kelas yang bernama TambahDataActivity
class TambahDataActivity : AppCompatActivity(){

    // Untuk membuat variable db yang berisi instance dari class NoteDB
    // Fungsi Lazy digunakan untuk mendeklarasikan nilai properti saat pertama kali dijalankan
    private val db by lazy { NoteDB(this) }

    // Untuk memberikan nilai 0 pada variable noteId
    private var noteId: Int = 0

    private lateinit var binding : ActivityTambahDataBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Untuk memanggil ActivityTambahDataBinding dengan menggunakan binding
        binding = ActivityTambahDataBinding.inflate(layoutInflater)

        // Untuk mendeklarasikan fungsi-fungsi yang akan dipanggil
        setContentView(binding.root)
        setupView()
        setupListener()
    }

    // Untuk membuat fungsi setupView(), didalamnya terdapat perintah untuk menambahkan button navigation up
    private fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType){
            // Untuk menulis data baru (button yang akan ditampilkan berupa button save)
            Constant.TYPE_CREATE -> {
                binding.buttonUpdate.visibility = View.GONE
            }
            // Untuk membaca data
            Constant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
            // Untuk mengedit data (button yang akan ditampilkan berupa button update)
            Constant.TYPE_UPDATE -> {
                binding.buttonSave.visibility = View.GONE
                getNote()
            }
        }
    }

    // Terdapat fungsi setupListener() yang berfungsi untuk memberikan aksi ketika button di klik
    private fun setupListener() {

        // Untuk memberikan fungsi klik listener pada buttonSave ketika di klik maka fungsi addNote() akan dipanggil dan terjadi penambahan data.
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, binding.editJudul.text.toString(), binding.editNama.text.toString(), binding.editTTL.text.toString(), binding.editAlamat.text.toString(), binding.editJK.text.toString(), binding.editRiwayat.text.toString())
                )
                finish()
            }
        }

        // Untuk memberikan fungsi klik listener pada buttonUpdate ketika di klik maka fungsi updateNote() aktif dan akan mengupdate data yang telah diubah.
        binding.buttonUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updateNote(
                    Note(noteId, binding.editJudul.text.toString(), binding.editNama.text.toString(), binding.editTTL.text.toString(), binding.editAlamat.text.toString(), binding.editJK.text.toString(), binding.editRiwayat.text.toString())
                )
                finish()
            }
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama getNote
    // Yang berfungsi untuk mengambil data yang kita pilih dan menampilkannya ke EditText.
    fun getNote(){
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNote(noteId)[0]
            binding.editJudul.setText(notes.title)
            binding.editNama.setText(notes.nama)
            binding.editTTL.setText(notes.ttl)
            binding.editAlamat.setText(notes.alamat)
            binding.editJK.setText(notes.jk)
            binding.editRiwayat.setText(notes.riwayat)
        }
    }

    // Fungsi untuk kembali ke halaman sebelumnya
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
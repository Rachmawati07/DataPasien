package com.rachma.datapasien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachma.datapasien.databinding.ActivityDaftarPasienBinding
import com.rachma.datapasien.room.Constant
import com.rachma.datapasien.room.Note
import com.rachma.datapasien.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Untuk mendeklarasikan class yang bernama DaftarPasienActivity
// Dan untuk menginisialisasi variabel binding untuk ActivityDaftarPasienBinding
class DaftarPasienActivity : AppCompatActivity(){
    private lateinit var binding : ActivityDaftarPasienBinding
    private lateinit var noteAdapter: NoteAdapter

    // Fungsi Lazy digunakan untuk mendeklarasikan nilai properti saat pertama kali dijalankan
    private val db by lazy { NoteDB(this) }

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Untuk memanggil ActivityDaftarPasienBinding dengan menggunakan binding
        binding = ActivityDaftarPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendeklarasikan fungsi setupRecyclerView()
        setupRecyclerView()
    }

    // Untuk memanggil kelas onStart agar menjalankan fungsi loadNote()
    override fun onStart() {
        super.onStart()
        loadNote()
    }

    // Mendeklarasikan fungsi yang bernama loadNote
    private fun loadNote(){
        // Fungsi yang membuat coroutine dan mengirimkan eksekusi isi fungsinya ke dispatcher yang sesuai
        CoroutineScope(Dispatchers.IO).launch {
           // Untuk mendeklarasikan variabel notes dan mendapatkan nilai dari NoteDao
            val notes = db.noteDao().getNotes()
            Log.d("DaftarPasienActivity", "dbResponse: $notes")
            withContext(Dispatchers.Main){
                noteAdapter.setData(notes)
            }
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama intentEdit
    fun intentEdit(noteId: Int, intentType: Int){
        // Untuk menjalankan activity melalui intent menuju ke TambahDataActivity
        startActivity(
            Intent(applicationContext, TambahDataActivity::class.java)
                .putExtra("intent_id", noteId)
                .putExtra("intent_type", intentType)
        )
    }

    // Mendeklarasikan fungsi setupRecyclerView yang akan memanggil activity noteAdapter
    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener{
            // Untuk memanggil kelas onRead untuk mendapatkan id dari parameter note dan akan mmembaca datanya
            override fun onRead(note: Note) {
                intentEdit(note.id, Constant.TYPE_READ)
            }

            // Untuk memanggil kelas onUpdate untuk mendapatkan id dari parameter note dan akan mengubah datanya
            override fun onUpdate(note: Note) {
                intentEdit(note.id, Constant.TYPE_UPDATE)
            }

            // Untuk memanggil kelas onDelete untuk menghapus data
            override fun onDelete(note: Note) {
                deleteDialog(note)
            }

        })

        // Untuk memanggil listnote dengan menggunakan binding dan menampilkan datanya melalui noteAdapter
        binding.listNote.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama deleteDialog
    // Yang berisi eksekusi hapus data
    private fun deleteDialog(note: Note){
        val alertDialog = AlertDialog.Builder(this)
        // Jika button hapus diklik maka akan menampilkan pesan alert dengan judul Konfirmasi dan pesannya berupa Apakah yakin menghapus (memanggil nilai dari judul).
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Apakah yakin menghapus ${note.title}?" )
            // Jika mengklik button batal maka tampilan dialog konfimasi akan dihilangkan
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            // Jika mengklik button hapus, maka akan menghapus data yang dipilih
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().deleteNote(note)
                    loadNote()
                }
            }
        }
        alertDialog.show()
    }
}
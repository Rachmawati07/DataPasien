package com.rachma.datapasien

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rachma.datapasien.room.Note
import kotlinx.android.synthetic.main.adapter_main.view.*

// Untuk mendeklarasikan class yang bernama NoteAdapter
class NoteAdapter (private var notes: ArrayList<Note>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.adapter_main,
                    parent,
                    false
                )
        )
    }

    // Untuk memanggil kelas getItemCount
    override fun getItemCount() = notes.size

    // Untuk memanggil kelas onBindViewHolder dengan menyertakan NoteViewHolder
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        // Ketika tampilan teks title diklik maka akan menjalankan fungsi onRead(note) untuk membaca data
        holder.view.text_title.text = note.title
        holder.view.text_title.setOnClickListener {
            listener.onRead(note)
        }

        // Jika icon_edit diklik maka akan menjalankan fungsi onUpdate(note) untuk mengubah data
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(note)
        }

        // Jika icon_delete diklik maka akan menjalankan fungsi onDelete(note) untuk menghapus data
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(note)
        }

    }

    inner class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Untuk meneklarasikan fungsi setData
    // Untuk menampilkan data yang didapat dari notes kedalam listview
    fun setData(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    // Untuk mendeklarasikan interface yang bernama OnAdapterListener
    // Yang berisi fungsi onRead, onUpdate, onDelete dari Note
    interface OnAdapterListener {
        fun onRead(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}
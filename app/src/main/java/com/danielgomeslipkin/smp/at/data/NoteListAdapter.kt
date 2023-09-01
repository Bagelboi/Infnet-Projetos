package com.danielgomeslipkin.smp.at.data

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.danielgomeslipkin.smp.at.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


//TODO: Import your model when you first use this template
class NoteListAdapter(private val OnClick : (Note) -> Unit, private val onRemoveClick : (Note) -> Unit) : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), OnClick, onRemoveClick)
    }

    class NoteViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Note, OnClick : (Note) -> Unit, onRemoveClick : (Note) -> Unit) = with(itemView) {
            itemView.setOnClickListener { OnClick(item) }
            val img = itemView.findViewById<ImageView>(R.id.imgListNote)
            val txt =  itemView.findViewById<TextView>(R.id.txtListNoteName)
            val removebtn =  itemView.findViewById<FloatingActionButton>(R.id.btnListNoteRemove)

            val bmp = item.getBitmap()
            if (bmp != null)
                img.setImageBitmap( item.getBitmap() )
            txt.setText(item.title)
            removebtn.setOnClickListener {
                onRemoveClick(item)
            }

        }

        //For inflating the layout in onCreateViewHolder()
        //TODO: Make sure your layout name is resolved
        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.list_note, parent, false)
                return NoteViewHolder(view)
            }
        }
    }
}

class NoteDiffCallback() : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        //TODO: Confirm that your id variable matches this one or change this one to match
        //the one in your model
        return oldItem.title == newItem.title && oldItem.email  == newItem.email
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.content == newItem.content
    }
}
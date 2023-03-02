package com.example.firstproj.Adabter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproj.Model.Note
import com.example.firstproj.R
import org.w3c.dom.Text

class NoteRV(var activity: Activity, var notesList:ArrayList<Note>) :RecyclerView.Adapter<NoteRV.NoteViewHolder>(){


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name:TextView =itemView.findViewById(R.id.name)
        var desc:TextView=itemView.findViewById(R.id.desc)
        var num:TextView=itemView.findViewById(R.id.num)

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

     var view = LayoutInflater.from(activity).inflate(R.layout.list_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
      return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.name.text=notesList[position].name
        holder.desc.text=notesList[position].desc
        holder.num.text=notesList[position].num
    }
}
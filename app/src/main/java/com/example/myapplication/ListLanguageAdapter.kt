package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListLanguageAdapter(private val listLanguage: ArrayList<Language>) : RecyclerView.Adapter<ListLanguageAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgPhoto: ImageView = view.findViewById(R.id.img_item_photo)
        var tvName: TextView = view.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = view.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_language, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, _, photo) = listLanguage[position]

        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listLanguage[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listLanguage.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Language)
    }
}
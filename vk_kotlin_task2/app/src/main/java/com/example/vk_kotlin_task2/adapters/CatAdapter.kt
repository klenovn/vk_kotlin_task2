package com.example.vk_kotlin_task2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vk_kotlin_task2.R
import com.example.vk_kotlin_task2.models.CatModel


class CatAdapter(private val cats: MutableList<CatModel>) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = cats[position]
        holder.bind(cat)
    }

    fun updateCats(newCats: List<CatModel>) {
        cats.addAll(newCats)
        notifyDataSetChanged()
    }

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catImageView: ImageView = itemView.findViewById(R.id.catImageView)
        private val loadingProgressBar: ProgressBar = itemView.findViewById(R.id.loadingProgressBar)

        fun bind(cat: CatModel) {
            Glide.with(itemView)
                .load("https://cataas.com/cat/${cat.id}")
                .apply(RequestOptions().fitCenter())
                .into(catImageView)

            loadingProgressBar.visibility = View.VISIBLE
        }
    }
}
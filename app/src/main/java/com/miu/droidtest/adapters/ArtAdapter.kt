package com.miu.droidtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.miu.droidtest.R
import com.miu.droidtest.db.Art
import javax.inject.Inject

class ArtAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ArtAdapter.ArtViewHolder>() {


    class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }


    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var arts: List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return ArtViewHolder(view)
    }


    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imgArt)
        val nameText = holder.itemView.findViewById<TextView>(R.id.txtArtName)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.txtArtistName)
        val yearText = holder.itemView.findViewById<TextView>(R.id.txtArtYear)
        val art = arts[position]
        holder.itemView.apply {
            glide.load(art.imgUrl).into(imageView)
            nameText.text = "Name: ${art.artName}"
            artistNameText.text = "Artist Name: ${art.artistName}"
            yearText.text = "Year: ${art.artYear}"
        }
    }

    override fun getItemCount(): Int {
        return arts.size
    }

}
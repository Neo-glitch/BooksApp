package com.neo.booksrxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neo.booksrxjava.R
import com.neo.booksrxjava.network.model.VolumeInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.books_rv_items.view.*
import kotlin.collections.ArrayList

class BooksRvAdapter: RecyclerView.Adapter<BooksRvAdapter.MyViewHolder>() {
    var booksListData = ArrayList<VolumeInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_rv_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(booksListData[position])
    }

    override fun getItemCount(): Int = booksListData.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.title
        val publisher = itemView.publisher
        val description = itemView.description
        val thumbImage = itemView.thumbnail

        fun bind(volumeInfo: VolumeInfo) {
            title.text = volumeInfo.volumeInfo.title
            publisher.text = volumeInfo.volumeInfo.publisher
            description.text = volumeInfo.volumeInfo.description
            val imageUrl = volumeInfo.volumeInfo.imageLinks.smallThumbnail

            Picasso.get()
                .load(imageUrl)
                .into(thumbImage)

        }
    }
}
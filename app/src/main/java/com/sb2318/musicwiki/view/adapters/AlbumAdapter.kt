package com.sb2318.musicwiki.view.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.GenereDetailsItemBinding
import com.sb2318.musicwiki.model.Album


class AlbumAdapter(private val albums:List<Album>,private val context:Context):RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {

        val binding= DataBindingUtil.inflate<GenereDetailsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.genere_details_item,parent,false)

        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        holder.bind(albums[position])
    }

    inner class AlbumViewHolder(val binding:GenereDetailsItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(album: Album) {

            binding.title.text= album.name
            binding.artist.text= album.artist.name

            //Load Images
            album.image.let {
                Glide.with(context).load(it[1].text).into(binding.imageBg)
            }

        }
    }
}
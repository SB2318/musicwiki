package com.sb2318.musicwiki.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.GenereDetailsItemBinding
import com.sb2318.musicwiki.model.artist.Artist


class ArtistAdapter(private val artists:List<Artist>, private val listener:ArtistClickListener):
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {

        val binding= DataBindingUtil.inflate<GenereDetailsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.genere_details_item,parent,false)

        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {

        holder.bind(artists[position])
    }

    inner class ArtistViewHolder(val binding: GenereDetailsItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(artist: Artist) {

            binding.title.text= artist.name
            binding.artist.visibility= View.GONE

           binding.cardviewItem.setOnClickListener {
            listener.onArtistClick(artist)
          }
        }

    }

    interface ArtistClickListener{

        fun onArtistClick(artist:Artist)
    }
}
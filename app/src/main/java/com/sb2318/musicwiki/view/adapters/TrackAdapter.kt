package com.sb2318.musicwiki.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.GenereDetailsItemBinding
import com.sb2318.musicwiki.model.Track

class TrackAdapter(private val tracks:List<Track>,private val context:Context):RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {

        val binding= DataBindingUtil.inflate<GenereDetailsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.genere_details_item,parent,false)

        return TrackViewHolder(binding)
    }

    override fun getItemCount(): Int = tracks.size



    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
       holder.bind(tracks[position])
    }

    inner class TrackViewHolder(val binding: GenereDetailsItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(track: Track) {

            binding.title.text= track.name
            binding.artist.text= track.artist.name

            track.image.let {
                Glide.with(context).load(it[1].text).into(binding.imageBg)
            }
        }

    }
}
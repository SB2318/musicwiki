package com.sb2318.musicwiki.view.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.GenericItemBinding
import com.sb2318.musicwiki.model.Tag
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.viewModel.GenericViewModel

class TagAdapter(private var listOfTags:List<Tag>)
    : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    private var listItemCount=10

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {

        val binding= DataBindingUtil.inflate<GenericItemBinding>(LayoutInflater.from(parent.context),
                                             R.layout.generic_item,parent,false)

        return TagViewHolder(binding)
    }


    override fun getItemCount(): Int {
       return listItemCount
    }


    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
       holder.bind(listOfTags[position])
    }

    fun submitList(tags:List<Tag>){
        listOfTags= tags
        notifyDataSetChanged()
    }

    fun changeItemCount(choice:Boolean){

        listItemCount = if(choice)
            listOfTags.size
        else
            10

        notifyDataSetChanged()
    }

    inner class TagViewHolder(val binding:GenericItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(tag: Tag) {
            binding.tagName.text= tag.name
        }
    }


}
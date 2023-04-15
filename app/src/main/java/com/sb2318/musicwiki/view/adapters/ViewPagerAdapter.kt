package com.sb2318.musicwiki.view.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sb2318.musicwiki.view.fragments.details.AlbumDetailsFragment
import com.sb2318.musicwiki.view.fragments.details.ArtistDetailsFragment
import com.sb2318.musicwiki.view.fragments.details.TrackDetailsFragment

class ViewPagerAdapter(private val activity: FragmentActivity,private val tag:String): FragmentStateAdapter(activity) {


    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {

       return  when(position){

            0-> {
              val album=  AlbumDetailsFragment()
              val bundle= Bundle()
              bundle.putString("TAG_VALUE",tag)
              album.arguments= bundle
              album
            }

            1-> {
                val artist=   ArtistDetailsFragment()
                val bundle= Bundle()
                bundle.putString("TAG_VALUE",tag)
                artist.arguments= bundle
                artist
            }

            2->{
                val track= TrackDetailsFragment()
                val bundle= Bundle()
                bundle.putString("TAG_VALUE",tag)
                track.arguments= bundle
                track
            }

            else->
                AlbumDetailsFragment()
        }
    }
}
package com.sb2318.musicwiki.view.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentAlbumProflieBinding

class AlbumProfileFragment: Fragment() {

    private lateinit var binding:FragmentAlbumProflieBinding
    private val args:AlbumProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_album_proflie,container,false)
        return binding.root
    }
}
package com.sb2318.musicwiki.view.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentArtistProfileBinding
import com.sb2318.musicwiki.model.album.Album
import com.sb2318.musicwiki.model.tag.Tag
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.view.adapters.AlbumAdapter
import com.sb2318.musicwiki.view.adapters.TagAdapter
import com.sb2318.musicwiki.view.adapters.TrackAdapter
import com.sb2318.musicwiki.viewModel.getModifiedTextFromHTML
import com.sb2318.musicwiki.viewModel.processString

class ArtistProfileFragment:Fragment(), TagAdapter.ClickHandler, AlbumAdapter.AlbumClickListener {

    private lateinit var binding:FragmentArtistProfileBinding
    private lateinit var fragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragment =
            (requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)

        navController = fragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_artist_profile,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity

        arguments?.let{

            val artistName= it.getString("ARTIST_NAME")

            artistName?.let{
                 name->

                activity.viewModel.getArtistInfo(name,getString(R.string.api_key))
                activity.viewModel.getArtistTopTags(name,getString(R.string.api_key))
                activity.viewModel.getArtistTopAlbums(name,getString(R.string.api_key))
                activity.viewModel.getArtistTopTracks(name,getString(R.string.api_key))
            }
        }

        // Observe artist info and make changes in UI

        activity.viewModel.artistInfo.observe(viewLifecycleOwner){

            it?.let{
                item->
                binding.artistName.text= item.name
                binding.playCount.text= processString(item.stats.playcount)
                binding.followersCount.text= processString(item.stats.listeners)
                val text= getModifiedTextFromHTML(item.bio.summary)
                binding.descriptionTextview.text= text

            }
        }

        //Observe artist tags
        activity.viewModel.listTopTags.observe(viewLifecycleOwner){

            it?.let{
                item->
                val adapter= TagAdapter(item,this@ArtistProfileFragment)
                val layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                adapter.changeItemCount(true)
                binding.tagRecycler.layoutManager= layoutManager
                binding.tagRecycler.adapter = adapter
                binding.tagRecycler.setHasFixedSize(true)
            }
        }

        // Observe artist tracks
        activity.viewModel.listOfTracks.observe(viewLifecycleOwner){

            it?.let{
                item->
                val adapter= TrackAdapter(item,requireContext())
                val layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                binding.topTracksRecycler.adapter = adapter
                binding.topTracksRecycler.layoutManager= layoutManager
                binding.topTracksRecycler.setHasFixedSize(true)
            }
        }

        //Observe artist albums
        activity.viewModel.listOfAlbums.observe(viewLifecycleOwner){

            it?.let{
                item->
                val adapter= AlbumAdapter(item,requireContext(),this@ArtistProfileFragment)
                val layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                binding.topAlbumsRecycler.adapter= adapter
                binding.topAlbumsRecycler.layoutManager = layoutManager
                binding.topAlbumsRecycler.setHasFixedSize(true)
            }
        }

        //Set Back Listener
        binding.backListener.setOnClickListener {
            navController.popBackStack()
        }

    }

    override fun onClick(tag: Tag) {
        val bundle = Bundle()
        bundle.putString("TAG_NAME",tag.name)
        navController.navigate(R.id.detailsFragment,bundle)
    }

    override fun onAlbumClick(album: Album) {
        val bundle= Bundle()
        bundle.putString("ALBUM",album.name)
        bundle.putString("ARTIST",album.artist.name)
        navController.navigate(R.id.albumProfileFragment,bundle)
    }
}
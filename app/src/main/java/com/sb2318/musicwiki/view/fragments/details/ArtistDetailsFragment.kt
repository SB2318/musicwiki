package com.sb2318.musicwiki.view.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentGenereDetailsBinding
import com.sb2318.musicwiki.model.artist.Artist
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.view.adapters.ArtistAdapter

class ArtistDetailsFragment: Fragment(), ArtistAdapter.ArtistClickListener {

    private lateinit var binding: FragmentGenereDetailsBinding
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
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_genere_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity

        arguments?.let{

            val tag= it.getString("TAG_VALUE")
            if (tag != null) {
                activity.viewModel.getTopArtists(tag, getString(R.string.api_key))
            }
        }

        activity.viewModel.listOfArtists.observe(viewLifecycleOwner){

            it?.let{

                val layoutManager= GridLayoutManager(requireContext(),2)
                val adapter = ArtistAdapter(it,this@ArtistDetailsFragment)
                binding.recyclerContainer.adapter = adapter
                binding.recyclerContainer.layoutManager= layoutManager
                binding.recyclerContainer.setHasFixedSize(true)
            }
        }
    }

    override fun onArtistClick(artist: Artist) {

        val bundle= Bundle()
        bundle.putString("ARTIST_NAME",artist.name)

        navController.navigate(R.id.artistProfileFragment,bundle)
    }
}
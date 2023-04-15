package com.sb2318.musicwiki.view.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentGenereDetailsBinding
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.view.adapters.TrackAdapter

class TrackDetailsFragment: Fragment() {

    private lateinit var binding: FragmentGenereDetailsBinding

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
                activity.viewModel.getTopTracks(tag, getString(R.string.api_key))
            }
        }

        activity.viewModel.listOfTracks.observe(viewLifecycleOwner){

            it?.let{

                val layoutManager= GridLayoutManager(requireContext(),2)
                val adapter = TrackAdapter(it,requireContext())
                binding.recyclerContainer.adapter = adapter
                binding.recyclerContainer.layoutManager= layoutManager
                binding.recyclerContainer.setHasFixedSize(true)
            }
        }
    }
}
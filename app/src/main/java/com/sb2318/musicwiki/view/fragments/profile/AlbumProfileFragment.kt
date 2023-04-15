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
import com.sb2318.musicwiki.databinding.FragmentAlbumProflieBinding
import com.sb2318.musicwiki.model.tag.Tag
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.view.adapters.TagAdapter
import com.sb2318.musicwiki.view.fragments.GenericFragmentDirections
import com.sb2318.musicwiki.viewModel.getModifiedTextFromHTML

class AlbumProfileFragment: Fragment(), TagAdapter.ClickHandler {

    private lateinit var binding:FragmentAlbumProflieBinding



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

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_album_proflie,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity

       arguments?.let{

         val albumName = it.getString("ALBUM")
        val  artistName= it.getString("ARTIST")

           if (albumName != null && artistName != null) {

               activity.viewModel.getAlbumInfo(albumName,artistName,getString(R.string.api_key))
               activity.viewModel.getTopTagsByAlbum(artistName,albumName,getString(R.string.api_key))

           }
       }



        activity.viewModel.albumInfo.observe(viewLifecycleOwner){

            it?.let {

                it.wiki?.let{itW->
                    val text = getModifiedTextFromHTML(itW.summary)
                    binding.descriptionTextview.text= text
                }

                //Load ImageView
                Glide.with(requireContext()).load(it.image[1].text)
                    .into(binding.imageBg)
                binding.albumName.text= it.name
                binding.artistName.text  = it.artist
            }
        }

        activity.viewModel.listTopTags.observe(viewLifecycleOwner){

            it?.let{

                val adapter= TagAdapter(it,this@AlbumProfileFragment)
                val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                binding.tagRecycler.adapter = adapter
                adapter.changeItemCount(true)
                binding.tagRecycler.layoutManager = layoutManager
                binding.tagRecycler.setHasFixedSize(true)
            }
        }

        binding.backListener.setOnClickListener {
            navController.popBackStack()
        }

    }



    override fun onClick(tag: Tag) {

        val bundle = Bundle()
        bundle.putString("TAG_NAME",tag.name)
        navController.navigate(R.id.detailsFragment,bundle)
    }
}
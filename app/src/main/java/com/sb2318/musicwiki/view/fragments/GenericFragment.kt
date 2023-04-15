package com.sb2318.musicwiki.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.GenericLayoutScreenBinding
import com.sb2318.musicwiki.model.tag.Tag
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.view.adapters.TagAdapter

class GenericFragment: Fragment(), TagAdapter.ClickHandler {

    private lateinit var binding:GenericLayoutScreenBinding
    private lateinit var adapter:TagAdapter
    private lateinit var layoutManager:LinearLayoutManager
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

        binding= DataBindingUtil.inflate(inflater, R.layout.generic_layout_screen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as MainActivity

        activity.viewModel.getGeneres(getString(R.string.api_key))

        //Observe all the generes
        activity.viewModel.listTopTags.observe(requireActivity()){ tags->

            tags?.let{
                layoutManager= GridLayoutManager(requireContext(),3)
                adapter= TagAdapter(tags,this@GenericFragment)

                binding.genericRecycler.adapter= adapter
                binding.genericRecycler.layoutManager= layoutManager
                binding.genericRecycler.setHasFixedSize(true)
            }
        }

        binding.expandLessListener.setOnClickListener{
            binding.expandLessListener.visibility= View.GONE
            binding.expandMoreListener.visibility= View.VISIBLE
            adapter.changeItemCount(true)
        }

        binding.expandMoreListener.setOnClickListener {
            binding.expandLessListener.visibility= View.VISIBLE
            binding.expandMoreListener.visibility= View.GONE
            adapter.changeItemCount(false)
        }

    }

    override fun onClick(tag: Tag) {

          val bundle = Bundle()
          bundle.putString("TAG_NAME",tag.name)
          navController.navigate(R.id.detailsFragment,bundle)
    }
}
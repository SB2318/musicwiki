package com.sb2318.musicwiki.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.GenericLayoutScreenBinding
import com.sb2318.musicwiki.model.Tag
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.view.adapters.TagAdapter
import com.sb2318.musicwiki.viewModel.GenericViewModel

class GenericFragment: Fragment() {

    private lateinit var binding:GenericLayoutScreenBinding
    private lateinit var adapter:TagAdapter
    private lateinit var layoutManager:LinearLayoutManager

    private val viewModel by lazy {
        ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[GenericViewModel::class.java]
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

        viewModel.getGeneres(getString(R.string.api_key))

        //Observe all the generes
        viewModel.listTopTags.observe(requireActivity()){ tags->

            tags?.let{
                layoutManager= GridLayoutManager(requireContext(),3)
                adapter= TagAdapter(tags)

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
}
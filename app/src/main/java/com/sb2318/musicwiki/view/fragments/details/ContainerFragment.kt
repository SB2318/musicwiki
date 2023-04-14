package com.sb2318.musicwiki.view.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentContainerBinding
import com.sb2318.musicwiki.model.Tag

class ContainerFragment: Fragment() {

    private var tag:String?= null
    private lateinit var binding:FragmentContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= DataBindingUtil.inflate(inflater, R.layout.fragment_container,container,false)

        arguments?.let {
            tag= it.getString("TAG_VALUE")
        }
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




}
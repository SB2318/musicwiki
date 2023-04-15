package com.sb2318.musicwiki.view.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentContainerBinding
import com.sb2318.musicwiki.model.Tag
import com.sb2318.musicwiki.view.adapters.ViewPagerAdapter

class ContainerFragment: Fragment() {

    private var tag:String?= null
    private lateinit var binding:FragmentContainerBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

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

        tag?.let {
            pagerAdapter= ViewPagerAdapter(requireActivity(),it)
            binding.viewPager.adapter= pagerAdapter
        }


        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

       binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

           override fun onPageSelected(position: Int) {
               super.onPageSelected(position)
               binding.tabLayout.getTabAt(position)?.select()
           }
       })

    }

}
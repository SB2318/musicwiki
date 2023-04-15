package com.sb2318.musicwiki.view.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.FragmentDetailsBinding
import com.sb2318.musicwiki.view.MainActivity
import com.sb2318.musicwiki.viewModel.getModifiedTextFromHTML


class DetailsFragment: Fragment() {

    private lateinit var binding:FragmentDetailsBinding
    private val args:DetailsFragmentArgs by navArgs()
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

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set Fragment Container
        val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        val containerFragment= ContainerFragment()
        val bundle=Bundle()
        bundle.putString("TAG_VALUE",args.tag);
        containerFragment.arguments= bundle
        ft.replace(R.id.fragment_container, containerFragment)
        ft.commit()

        // Set data
        val activity = requireActivity() as MainActivity
        binding.genereTitle.text= args.tag
        activity.viewModel.getTagInfo(args.tag,getString(R.string.api_key))

        activity.viewModel.tagInfo.observe(viewLifecycleOwner){
            it?.let{

                val text = getModifiedTextFromHTML(it.wiki.summary)

                binding.genereDescription.text = text.toString()
            }
        }

        binding.backListener.setOnClickListener {
            navController.popBackStack()
        }
    }
}
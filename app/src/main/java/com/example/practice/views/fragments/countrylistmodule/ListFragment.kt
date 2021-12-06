package com.example.practice.views.fragments.countrylistmodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentListBinding
import com.example.practice.extenssions.replaceFragment
import com.example.practice.views.fragments.countrylistmodule.countrylist.CountryListFragment

class ListFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecyclerViewSearch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_recyclerViewSearch -> replaceFragment(CountryListFragment())
        }
    }
}
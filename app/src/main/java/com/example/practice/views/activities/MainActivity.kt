package com.example.practice.views.activities

import android.os.Bundle
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.extenssions.replaceFragmentSafely
import com.example.practice.views.fragments.countrylist.CountryListFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        replaceFragmentSafely(CountryListFragment())

    }
}
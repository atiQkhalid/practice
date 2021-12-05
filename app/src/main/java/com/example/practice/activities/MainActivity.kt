package com.example.practice.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice.R
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.extensions.replaceFragmentSafely
import com.example.practice.fragments.HomeFragment
import com.example.practice.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        replaceFragmentSafely(HomeFragment(), containerViewId = binding.flFragment )
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragmentSafely(HomeFragment(), containerViewId = binding.flFragment )
                }
                R.id.search -> {
                    replaceFragmentSafely(SearchFragment(), containerViewId = binding.flFragment )
                }
            }
            true
        }

    }
}
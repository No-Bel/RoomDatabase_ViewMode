package com.example.roomdatabasenobel.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabasenobel.R
import com.example.roomdatabasenobel.adapter.MyAdapter
import com.example.roomdatabasenobel.databinding.ActivityMainBinding
import com.example.roomdatabasenobel.model.data.User
import com.example.roomdatabasenobel.view.fragments.AddFragment
import com.example.roomdatabasenobel.view.fragments.HomeFragment
import com.example.roomdatabasenobel.view.fragments.UpdateFragment

class MainActivity : AppCompatActivity(), HomeFragment.AddFabClick, AddFragment.GoBackToHomeScreen,
    MyAdapter.GoToUpdateFragment,
    UpdateFragment.GoBackHomeScreenFromUpdateFragment {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeScreenFragment()
    }

    private fun homeScreenFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }

    override fun addFabClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun goBackToHomeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun goToUpdateFragment(user: User) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, UpdateFragment(user))
            .addToBackStack(null)
            .commit()
    }

    override fun goBackHomeScreenFromUpdateFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .addToBackStack(null)
            .commit()
    }
}
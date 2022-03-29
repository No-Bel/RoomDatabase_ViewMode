package com.example.roomdatabasenobel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabasenobel.adapter.MyAdapter
import com.example.roomdatabasenobel.data.User
import com.example.roomdatabasenobel.fragment.AddFragment
import com.example.roomdatabasenobel.fragment.HomeFragment
import com.example.roomdatabasenobel.fragment.UpdateFragment

class MainActivity : AppCompatActivity(), HomeFragment.AddFabClick, AddFragment.GoBackToHomeScreen, MyAdapter.GoToUpdateFragment,
    UpdateFragment.GoBackHomeScreenFromUpdateFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
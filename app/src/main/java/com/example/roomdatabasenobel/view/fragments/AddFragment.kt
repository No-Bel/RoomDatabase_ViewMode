package com.example.roomdatabasenobel.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasenobel.model.data.User
import com.example.roomdatabasenobel.model.data.UserDao
import com.example.roomdatabasenobel.model.data.UserDatabase
import com.example.roomdatabasenobel.databinding.FragmentAddBinding
import com.example.roomdatabasenobel.model.repository.UserRepo
import com.example.roomdatabasenobel.viewmodel.MainViewModelFactory
import com.example.roomdatabasenobel.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var myViewModel: UserViewModel
    private lateinit var binding: FragmentAddBinding

    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var userDao: UserDao
    private lateinit var repo: UserRepo
    private lateinit var myDatabase: UserDatabase

    private var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        init()
        return view
    }

    private fun init() {
        myDatabase = UserDatabase.getDatabase(requireContext())
        userDao = myDatabase.userDao()
        repo = UserRepo(userDao)
        viewModelFactory = MainViewModelFactory(repo)
        myViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
            listener?.goBackToHomeScreen()
        }
        myViewModel.readAllDataVm.observe(viewLifecycleOwner, Observer {
            userId = it.size + 1
        })
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val age = addAge.text

        if (inputCheck(firstName, lastName, age)) {
            val user = User(userId, firstName, lastName, Integer.parseInt(age.toString()))
            /** ??????????????????????????? data-??? Database-?????? **/
            myViewModel.addUserVm(user)
            Toast.makeText(requireContext(), "Successfully added user!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }

    private var listener: GoBackToHomeScreen? = null

    interface GoBackToHomeScreen {
        fun goBackToHomeScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as GoBackToHomeScreen
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
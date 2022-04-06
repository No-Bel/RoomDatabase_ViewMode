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
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasenobel.model.data.User
import com.example.roomdatabasenobel.model.data.UserDao
import com.example.roomdatabasenobel.model.data.UserDatabase
import com.example.roomdatabasenobel.databinding.FragmentUpdateBinding
import com.example.roomdatabasenobel.model.repository.UserRepo
import com.example.roomdatabasenobel.viewmodel.MainViewModelFactory
import com.example.roomdatabasenobel.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment(user: User) : Fragment() {

    private var userList: User = user
    private lateinit var myViewModel: UserViewModel
    private lateinit var binding: FragmentUpdateBinding

    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var userDao: UserDao
    private lateinit var repo: UserRepo
    private lateinit var myDatabase: UserDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        init()
        showCurrentUser()
        return view
    }

    private fun showCurrentUser() {
        binding.updateFirstName.setText(userList.firstName)
        binding.updateLastName.setText(userList.lastName)
        binding.updateAge.setText(userList.age.toString())
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
        binding.updateBtn.setOnClickListener {
            updateUser()
        }

        binding.userDeleteFab.setOnClickListener {
            deleteUser()
        }
    }

    private fun updateUser() {
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = Integer.parseInt(binding.updateAge.text.toString())

        if (inputCheck(firstName, lastName, update_age.text)) {
            val updateUser = User(userList.id, firstName, lastName, age)
            myViewModel.updateUserVm(updateUser)
            listener?.goBackHomeScreenFromUpdateFragment()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }

    private fun deleteUser() {
        myViewModel.deleteUserVm(userList)
        val fullName = userList.firstName + " " + userList.lastName
        Toast.makeText(requireContext(), "Successfully removed: ${fullName}.", Toast.LENGTH_SHORT)
            .show()
        listener?.goBackHomeScreenFromUpdateFragment()
    }

    private var listener: GoBackHomeScreenFromUpdateFragment? = null

    interface GoBackHomeScreenFromUpdateFragment {
        fun goBackHomeScreenFromUpdateFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as GoBackHomeScreenFromUpdateFragment
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
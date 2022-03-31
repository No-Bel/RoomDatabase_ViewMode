package com.example.roomdatabasenobel.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasenobel.data.User
import com.example.roomdatabasenobel.databinding.FragmentUpdateBinding
import com.example.roomdatabasenobel.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment(user: User) : Fragment() {

    private var userList: User = user
    private lateinit var myViewModel: UserViewModel
    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        myViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        view.update_firstName.setText(userList.firstName)
        view.update_lastName.setText(userList.lastName)
        view.update_age.setText(userList.age.toString())

        return view
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
        val firstName = update_firstName.text.toString()
        val lastName = update_lastName.text.toString()
        val age = Integer.parseInt(update_age.text.toString())

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
        Toast.makeText(requireContext(), "Successfully removed: ${fullName}.", Toast.LENGTH_SHORT).show()
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
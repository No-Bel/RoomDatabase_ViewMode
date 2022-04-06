package com.example.roomdatabasenobel.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasenobel.R
import com.example.roomdatabasenobel.adapter.MyAdapter
import com.example.roomdatabasenobel.model.data.User
import com.example.roomdatabasenobel.model.data.UserDao
import com.example.roomdatabasenobel.model.data.UserDatabase
import com.example.roomdatabasenobel.databinding.FragmentHomeBinding
import com.example.roomdatabasenobel.model.repository.UserRepo
import com.example.roomdatabasenobel.viewmodel.MainViewModelFactory
import com.example.roomdatabasenobel.viewmodel.UserViewModel

class HomeFragment : Fragment(), MyAdapter.GoToUpdateFragment {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var myViewModel: UserViewModel
    private lateinit var repo: UserRepo
    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var userDao: UserDao
    private lateinit var myDatabase: UserDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        init()
        return view
    }

    private fun init() {
        myDatabase = UserDatabase.getDatabase(requireContext())
        userDao = myDatabase.userDao()
        repo = UserRepo(userDao)
        viewModelFactory = MainViewModelFactory(repo)

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        myAdapter = MyAdapter()
        recyclerView.adapter = myAdapter
        myViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        myViewModel.readAllDataVm.observe(viewLifecycleOwner, Observer {
            myAdapter.setData(it)
        })
        myAdapter.editUpdateUser(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addFab.setOnClickListener {
            listener?.addFabClick()
        }

        binding.allUserDeleteFab.setOnClickListener {
            deleteAllUser()
        }
    }

    private fun deleteAllUser() {
        myViewModel.deleteAllUserVm()
        Toast.makeText(requireContext(), "Successfully remove All Users.", Toast.LENGTH_SHORT).show()
    }

    private var listener: AddFabClick? = null

    interface AddFabClick {
        fun addFabClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as AddFabClick
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun goToUpdateFragment(user: User) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, UpdateFragment(user))
            ?.addToBackStack(null)
            /** change! **/
            ?.commit()
    }
}
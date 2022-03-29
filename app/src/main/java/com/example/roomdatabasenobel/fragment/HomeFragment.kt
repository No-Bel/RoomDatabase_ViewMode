package com.example.roomdatabasenobel.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasenobel.R
import com.example.roomdatabasenobel.adapter.MyAdapter
import com.example.roomdatabasenobel.data.User
import com.example.roomdatabasenobel.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), MyAdapter.GoToUpdateFragment {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var myViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerview)
        init()
        myAdapter.editUpdateUser(this)

        return view
    }

    private fun init() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        myAdapter = MyAdapter()
        recyclerView.adapter = myAdapter
        myViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        myViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            myAdapter.setData(it)
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_fab.setOnClickListener {
            listener?.addFabClick()
        }
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
            ?.commit()
    }
}
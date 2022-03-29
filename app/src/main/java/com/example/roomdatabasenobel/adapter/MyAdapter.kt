package com.example.roomdatabasenobel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasenobel.R
import com.example.roomdatabasenobel.data.User
import kotlinx.android.synthetic.main.list_holder.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var userList = ArrayList<User>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun updateUser(user: User) {
            itemView.setOnClickListener {
                listener?.goToUpdateFragment(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_item.text = currentItem.id.toString()
        holder.itemView.name.text = currentItem.firstName
        holder.itemView.lastName.text = currentItem.lastName
        holder.itemView.age.text = currentItem.age.toString()
        holder.updateUser(currentItem)

    }

    override fun getItemCount() = userList.size

    fun setData(user: List<User>) {
        this.userList.clear()
        this.userList.addAll(user)
        notifyDataSetChanged()
    }

    private var listener: GoToUpdateFragment? = null

    interface GoToUpdateFragment {
        fun goToUpdateFragment(user: User)
    }

    fun editUpdateUser(listener: GoToUpdateFragment) {
        this.listener = listener
    }
}
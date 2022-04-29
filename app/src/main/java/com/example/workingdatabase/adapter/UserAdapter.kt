package com.example.workingdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.workingdatabase.databinding.FragmentUserBinding
import com.example.workingdatabase.model.User
import com.example.workingdatabase.holder.UserViewHolder

class UserAdapter(
    private val context: Context,
    private val click: () -> Unit
) : ListAdapter<User, UserViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            binding = FragmentUserBinding.inflate(layoutInflater, parent, false),
            click = click
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun delete(position: Int, list: List<User>) {
        val listSw = list.toMutableList()
        listSw.removeAt(position)
       // notifyDataSetChanged()
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

}


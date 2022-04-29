package com.example.workingdatabase.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.workingdatabase.databinding.FragmentUserBinding
import com.example.workingdatabase.model.User

class UserViewHolder(
    private val binding: FragmentUserBinding,
    private val click: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.userName.text = user.name.toString()

        binding.root.setOnClickListener(){
            click()
        }
    }

}
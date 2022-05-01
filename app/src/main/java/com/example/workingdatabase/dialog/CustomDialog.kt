package com.example.workingdatabase.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.workingdatabase.R
import com.example.workingdatabase.adapter.UserAdapter
import com.example.workingdatabase.database.queryDatabase.ActionsWithDatabase
import com.example.workingdatabase.databinding.FragmentDialogBinding
import com.example.workingdatabase.filer.CustomFilter
import com.example.workingdatabase.model.User

class CustomDialog(
    private val user: User,
    private val adapter: UserAdapter
) : DialogFragment(), CustomFilter {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDialogBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editCustomDialogName.text?.filters = arrayOf(filter())

        binding.bottomCustomDialogYes.setOnClickListener {
            val name = binding.editCustomDialogName.text.toString()
            if (name.isNotEmpty()) {
                user.name = name
                Toast.makeText(context, R.string.changed_user, Toast.LENGTH_LONG).show()
            }
            adapter.updateUser(user)
            adapter.submitList(ActionsWithDatabase.QUERY_USERS(requireContext()))
            dismiss()
            adapter.notifyDataSetChanged()
        }

        binding.bottomCustomDialogNo.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
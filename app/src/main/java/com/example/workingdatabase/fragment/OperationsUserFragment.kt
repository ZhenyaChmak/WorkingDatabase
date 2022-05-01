package com.example.workingdatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.workingdatabase.R
import com.example.workingdatabase.filer.CustomFilter
import com.example.workingdatabase.database.queryDatabase.ActionsWithDatabase
import com.example.workingdatabase.databinding.FragmentUserOperationsBinding

class OperationsUserFragment : Fragment(), CustomFilter {

    private var _binding: FragmentUserOperationsBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserOperationsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameEditText.text?.filters = arrayOf(filter())

        binding.buttonAddUser.setOnClickListener {
            binding.nameEditText.text
                ?.takeIf { it.isNotEmpty() }
                ?.let { name ->
                    ActionsWithDatabase.INSERT_USER(requireContext(), name = name.toString())
                    Toast.makeText(context, R.string.add_user, Toast.LENGTH_LONG).show()
                    binding.edit.error = null
                } ?: run {
                binding.edit.error = "text is empty"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
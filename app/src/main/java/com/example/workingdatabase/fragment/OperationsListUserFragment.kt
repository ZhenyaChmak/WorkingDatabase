package com.example.workingdatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workingdatabase.database.appDatabase
import com.example.workingdatabase.databinding.FragmentUserListOperationsBinding
import com.example.workingdatabase.model.User

class OperationsListUserFragment : Fragment() {

    private var _binding: FragmentUserListOperationsBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListOperationsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        updateListDatabase()

        binding.buttonAdd.setOnClickListener {
            binding.nameEditText.text
                ?.takeIf { it.isNotEmpty() }
                ?.let { name ->
                    requireActivity().appDatabase.userDao()
                        .insertUser(User(name = name.toString()))
                    updateListDatabase()
                    binding.edit.error = null
                } ?: run {
                binding.edit.error = "text is empty"
            }
        }
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        val dsad = binding.name.text
        outState?.run {
            putString(NAME, dsad?.toString())
        }
        super.onSaveInstanceState(outState)
    }*/

    private fun updateListDatabase() {
        //binding.text.text = data.userDao().getUsers().joinToString(separator = "\n")





        binding.text.text =
            requireContext()
                .appDatabase
                .userDao()
                .getUsers()
                .joinToString(separator = "\n")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val NAME = "name"
    }
}
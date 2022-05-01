package com.example.workingdatabase.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workingdatabase.R
import com.example.workingdatabase.decoration.SwipeToDelete
import com.example.workingdatabase.adapter.UserAdapter
import com.example.workingdatabase.database.queryDatabase.ActionsWithDatabase
import com.example.workingdatabase.databinding.FragmentUserListBinding
import com.example.workingdatabase.decoration.addDecorationUser
import com.example.workingdatabase.dialog.CustomDialog
import com.example.workingdatabase.model.User

class ListUserFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        UserAdapter(requireContext()) {
            editUser(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listUsers.adapter = adapter
        binding.listUsers.layoutManager = LinearLayoutManager(requireContext())

        binding.listUsers.addDecorationUser(BOTTOM_DECORATION_USER)
        adapter.submitList(ActionsWithDatabase.QUERY_USERS(requireContext()))
        swipeDeleteUserFromDatabase()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun swipeDeleteUserFromDatabase() {
        val swipeDelete = object : SwipeToDelete(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                alertDialogUserSwipe(viewHolder)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeDelete)
        itemTouchHelper.attachToRecyclerView(binding.listUsers)
    }

    private fun editUser(user: User) {
        CustomDialog(user, adapter).show(childFragmentManager, null)
    }

    private fun alertDialogUserSwipe(viewHolder: RecyclerView.ViewHolder) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setIcon(R.drawable.ic_delete)
            .setCancelable(false)
            .setMessage("Are you sure you want to delete user?")
            .setPositiveButton("OK") { _, _ ->
                adapter.delete(viewHolder.adapterPosition)
                adapter.submitList(ActionsWithDatabase.QUERY_USERS(requireContext()))
                Toast.makeText(context, R.string.delete_user, Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { _, _ ->
                adapter.notifyItemChanged(viewHolder.adapterPosition)
            }
            .show()
    }

    companion object {
        const val BOTTOM_DECORATION_USER = 20
    }

}
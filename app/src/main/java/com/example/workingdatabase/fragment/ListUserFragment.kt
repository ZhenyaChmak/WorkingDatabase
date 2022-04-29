package com.example.workingdatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workingdatabase.SwipeToDelete
import com.example.workingdatabase.adapter.UserAdapter
import com.example.workingdatabase.database.appDatabase
import com.example.workingdatabase.databinding.FragmentUserListBinding
import com.example.workingdatabase.decoration.addDecorationUser
import com.example.workingdatabase.model.User

class ListUserFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        UserAdapter(requireContext()){
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

        binding.swipeRefreshListUsers.setOnRefreshListener {
            binding.swipeRefreshListUsers.isRefreshing = false
        }

        binding.listUsers.adapter = adapter
        binding.listUsers.layoutManager = LinearLayoutManager(requireContext())


        //TODO name
        val sasd =requireContext().appDatabase.userDao().getUsers()

        swipeDeleteUserFromDatabase(sasd)

        //TODO razmer
        binding.listUsers.addDecorationUser(10)

        adapter.submitList(sasd)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun swipeDeleteUserFromDatabase(list:List<User>){
        val swipeDelete = object : SwipeToDelete(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val asd = adapter.currentList.toList().toMutableList()
                adapter.delete(viewHolder.adapterPosition, list)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeDelete)
        itemTouchHelper.attachToRecyclerView(binding.listUsers)
    }

    companion object{
       /* private const val USER = "user"
        fun  newInstance(name : String) : ListUserFragment{
            return ListUserFragment().apply {
                arguments = bundleOf(
                    USER to name
                )
            }
        }*/
    }
}
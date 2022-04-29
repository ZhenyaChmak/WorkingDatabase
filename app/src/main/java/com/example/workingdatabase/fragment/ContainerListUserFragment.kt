package com.example.workingdatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.workingdatabase.R
import com.example.workingdatabase.databinding.FragmentUserListContainerBinding

class ContainerListUserFragment : Fragment() {
    private var _binding: FragmentUserListContainerBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListContainerBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuController =
            (childFragmentManager.findFragmentById(R.id.fragment_container_user_list) as NavHostFragment)
                .navController
        binding.bottomNavigation.setupWithNavController(menuController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
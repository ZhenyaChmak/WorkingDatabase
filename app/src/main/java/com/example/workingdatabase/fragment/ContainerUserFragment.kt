package com.example.workingdatabase.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.workingdatabase.R
import com.example.workingdatabase.databinding.FragmentUserContainerBinding

class ContainerUserFragment : Fragment() {
    private var _binding: FragmentUserContainerBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserContainerBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuController =
            (childFragmentManager.findFragmentById(R.id.fragment_user_container) as NavHostFragment)
                .navController
        binding.bottomNavigation.setupWithNavController(menuController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
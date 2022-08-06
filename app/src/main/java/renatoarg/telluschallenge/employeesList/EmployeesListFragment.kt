package renatoarg.telluschallenge.employeesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeesListBinding

@AndroidEntryPoint
class EmployeesListFragment : Fragment() {

    private lateinit var binding: FragmentEmployeesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeesListBinding.inflate(layoutInflater)
        return binding.root
    }

}
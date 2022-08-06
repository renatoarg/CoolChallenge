package renatoarg.telluschallenge.ui.employeesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeesListBinding

@AndroidEntryPoint
class EmployeesListFragment : Fragment() {

    private lateinit var binding: FragmentEmployeesListBinding

    private val viewModel: EmployeesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.fetchEmployees()
    }

    private fun observeViewModel() {
        viewModel.employeesState.observe(viewLifecycleOwner) { employeeState ->
            when(employeeState) {
                is EmployeeState.OnFetchEmployees -> {
                }
                is EmployeeState.OnApiError -> {
                }
            }
        }
    }

}
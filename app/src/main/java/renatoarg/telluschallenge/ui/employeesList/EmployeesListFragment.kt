package renatoarg.telluschallenge.ui.employeesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeesListBinding
import renatoarg.telluschallenge.model.Employee
import renatoarg.telluschallenge.ui.employeesList.adapter.EmployeesAdapter

@AndroidEntryPoint
class EmployeesListFragment : Fragment() {

    private lateinit var binding: FragmentEmployeesListBinding
    private val adapter = EmployeesAdapter(callBack = { employee ->

    })

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
        setupUi()
        observeViewModel()
        viewModel.fetchEmployees()
    }

    private fun setupUi() {
        binding.employeesRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.employeesState.observe(viewLifecycleOwner) { employeeState ->
            Log.i("MONSTRO", "$employeeState")
            when(employeeState) {
                is EmployeeState.OnFetchEmployees -> onFetchEmployees(employeeState.employees)
                is EmployeeState.OnApiError -> onApiError()
                is EmployeeState.OnLoading -> onLoading(employeeState.isLoading)
            }
        }
    }

    private fun onFetchEmployees(employees: List<Employee>) {
        adapter.resetList(employees)
        binding.employeesRecyclerView.isVisible = true
    }

    private fun onApiError() {
        binding.employeesRecyclerView.isVisible = false
    }

    private fun onLoading(isLoading: Boolean) {
        binding.loadingLayout.isVisible = isLoading
    }

}
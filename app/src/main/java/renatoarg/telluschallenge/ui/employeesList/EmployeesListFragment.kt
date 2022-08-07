package renatoarg.telluschallenge.ui.employeesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeesListBinding
import renatoarg.telluschallenge.model.Employee
import renatoarg.telluschallenge.ui.employeesList.adapter.EmployeesAdapter

@AndroidEntryPoint
class EmployeesListFragment : Fragment() {

    private lateinit var binding: FragmentEmployeesListBinding

    private val adapter = EmployeesAdapter(onEmployeeClicked = { employee ->
        findNavController().navigate(
            EmployeesListFragmentDirections.actionEmployeesListFragmentToEmployeeDetailsFragment(employee)
        )
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
        binding.run {
            // RecyclerView Adapter
            employeesRecyclerView.adapter = adapter

            // Swipe to refresh
            swipeToRefreshLayout.setOnRefreshListener {
                fetchRefreshedEmployees()
                swipeToRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.employeesState.observe(viewLifecycleOwner) { employeeState ->
            when (employeeState) {
                is EmployeeState.OnFetchEmployees -> onFetchEmployees(employeeState.employees)
                is EmployeeState.OnApiError -> onApiError()
                is EmployeeState.OnLoading -> onLoading(employeeState.isLoading)
            }
        }
    }

    private fun onApiError() {
        adapter.resetList(emptyList())
        findNavController().navigate(EmployeesListFragmentDirections.actionEmployeesListFragmentToApiErrorFragment())
    }

    private fun fetchRefreshedEmployees() {
        viewModel.setRefresh()
        viewModel.fetchEmployees()
    }

    private fun onFetchEmployees(employees: List<Employee>) {
        adapter.resetList(employees)
        binding.employeesRecyclerView.isVisible = true
    }

    private fun onLoading(isLoading: Boolean) {
        binding.loadingLayout.isVisible = isLoading
    }

}
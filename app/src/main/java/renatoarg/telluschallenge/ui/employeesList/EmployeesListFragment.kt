package renatoarg.telluschallenge.ui.employeesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeesListBinding
import renatoarg.telluschallenge.model.Employee
import renatoarg.telluschallenge.ui.base.BaseFragment
import renatoarg.telluschallenge.ui.employeesList.adapter.EmployeesAdapter

@AndroidEntryPoint
class EmployeesListFragment : BaseFragment() {

    private lateinit var binding: FragmentEmployeesListBinding

    private val adapter = EmployeesAdapter(callBack = { employee ->
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
                viewModel.fetchEmployees()
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
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun onApiError() {
        adapter.resetList(emptyList())
        showAlertDialog(
            { // positive button
                viewModel.fetchEmployees()
            },
            { // negative button
                finish()
            }
        )
    }

    private fun onFetchEmployees(employees: List<Employee>) {
        adapter.resetList(employees)
        binding.employeesRecyclerView.isVisible = true
    }

    private fun onLoading(isLoading: Boolean) {
        binding.run {
            loading.loadingLayout.isVisible = isLoading
        }
    }

}
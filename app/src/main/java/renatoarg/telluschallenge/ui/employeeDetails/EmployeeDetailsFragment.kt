package renatoarg.telluschallenge.ui.employeeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeeDetailsBinding
import renatoarg.telluschallenge.model.Employee
import renatoarg.telluschallenge.ui.base.BaseFragment
import renatoarg.telluschallenge.ui.employeesList.EmployeeState
import renatoarg.telluschallenge.ui.employeesList.EmployeesViewModel

@AndroidEntryPoint
class EmployeeDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentEmployeeDetailsBinding

    private val viewModel: EmployeesViewModel by activityViewModels()

    private val args: EmployeeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.fetchEmployee(args.employee.id)
    }

    private fun observeViewModel() {
        viewModel.employeesState.observe(viewLifecycleOwner) { employeeState ->
            when (employeeState) {
                is EmployeeState.OnFetchEmployee -> onFetchEmployee(employeeState.employee)
                is EmployeeState.OnApiError -> onApiError()
                is EmployeeState.OnLoading -> onLoading(employeeState.isLoading)
                is EmployeeState.OnFetchEmployees -> {
                    // do nothing
                }
            }
        }
    }

    private fun onFetchEmployee(employee: Employee) {
        binding.run {
            nameTextView.text = employee.name
            salaryTextView.text = employee.salary.toString()
            ageTextView.text = employee.age.toString()
            wrapperLayout.isVisible = true
        }
    }

    private fun onApiError() {
        showAlertDialog(
            { // positive button
                viewModel.fetchEmployee(args.employee.id)
            },
            { // negative button
                finish()
            }
        )
    }

    private fun onLoading(isLoading: Boolean) {
        binding.loading.loadingLayout.isVisible = isLoading
    }
}
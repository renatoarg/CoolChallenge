package renatoarg.telluschallenge.ui.apiError

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentApiErrorBinding
import renatoarg.telluschallenge.ui.employeesList.EmployeesViewModel

@AndroidEntryPoint
class ApiErrorFragment : DialogFragment() {

    private lateinit var binding: FragmentApiErrorBinding

    private val viewModel: EmployeesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiErrorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            retryButton.setOnClickListener {
                dismiss()
                viewModel.setRefresh()
                viewModel.fetchEmployees()
            }
            exitButton.setOnClickListener {
                dismiss()
                requireActivity().finish()
            }
        }

    }

}
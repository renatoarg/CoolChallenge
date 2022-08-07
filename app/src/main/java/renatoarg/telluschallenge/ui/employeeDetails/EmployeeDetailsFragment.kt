package renatoarg.telluschallenge.ui.employeeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.R
import renatoarg.telluschallenge.databinding.FragmentEmployeeDetailsBinding
import renatoarg.telluschallenge.model.Employee
import renatoarg.telluschallenge.ui.toUsdCurrency

@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEmployeeDetailsBinding

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
        setupUi()
    }

    private fun setupUi() {
        val employee: Employee = args.employee
        binding.run {
            employee.run {
                nameTextView.text = name
                salaryTextView.text = getString(R.string.salary_value, salary.toUsdCurrency())
                ageTextView.text = getString(R.string.age_value, age)

                if (image.isEmpty()) return
                userImageView.load(image) {
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}
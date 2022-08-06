package renatoarg.telluschallenge.ui.employeeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import renatoarg.telluschallenge.databinding.FragmentEmployeeDetailsBinding

@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEmployeeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeDetailsBinding.inflate(layoutInflater)
        return binding.root
    }
}
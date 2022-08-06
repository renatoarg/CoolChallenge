package renatoarg.telluschallenge.apiError

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import renatoarg.telluschallenge.databinding.FragmentApiErrorBinding

class ApiErrorFragment : Fragment() {

    private lateinit var binding: FragmentApiErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiErrorBinding.inflate(layoutInflater)
        return binding.root
    }

}
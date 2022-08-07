package renatoarg.telluschallenge.ui.employeesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import renatoarg.telluschallenge.model.EmployeesRepository
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    private val employeesLiveData = MutableLiveData<EmployeeState>()
    val employeesState = employeesLiveData

    fun fetchEmployees() {
        if (!employeesRepository.isRefresh) return
        CoroutineScope(Dispatchers.IO).launch {
            emitEmployeeState(EmployeeState.OnLoading(true))
            val response = employeesRepository.getEmployees()
            emitEmployeeState(
                if (response.isSuccessful) EmployeeState.OnFetchEmployees(response.body()?.data.orEmpty())
                else EmployeeState.OnApiError
            )
            emitEmployeeState(EmployeeState.OnLoading(false))
        }
    }

    private fun emitEmployeeState(state: EmployeeState) {
        CoroutineScope(Dispatchers.Main).launch { employeesLiveData.value = state }
    }

    fun setRefresh() {
        employeesRepository.isRefresh = true
    }
}
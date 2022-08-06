package renatoarg.telluschallenge.ui.employeesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import renatoarg.telluschallenge.model.EmployeesRepository
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    private val employeesLiveData = MutableLiveData<EmployeeState>()
    val employeesState = employeesLiveData

    fun fetchEmployees() {
        GlobalScope.launch {
            emitEmployeeState(EmployeeState.OnLoading(true))
            val response = employeesRepository.getEmployees()
            emitEmployeeState(
                when (response.isSuccessful) {
                    true -> EmployeeState.OnFetchEmployees(response.body()?.data ?: emptyList())
                    false -> EmployeeState.OnApiError
                }
            )
            emitEmployeeState(EmployeeState.OnLoading(false))
        }
    }

    private fun emitEmployeeState(state: EmployeeState) {
        CoroutineScope(Dispatchers.Main).launch {
            employeesLiveData.value = state
        }
    }

}
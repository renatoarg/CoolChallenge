package renatoarg.telluschallenge.ui.employeesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
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
            val response = employeesRepository.getEmployees()
            if (response.isSuccessful) {
                employeesLiveData.postValue(EmployeeState.OnFetchEmployees(response.body()?.data ?: emptyList()))
            } else {
                employeesLiveData.postValue(EmployeeState.OnApiError)
            }
        }
    }

}
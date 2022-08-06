package renatoarg.telluschallenge.ui.employeesList

import renatoarg.telluschallenge.model.Employee

sealed class EmployeeState {

    object OnApiError : EmployeeState()

    data class OnFetchEmployees(
        val employees: List<Employee>
    ) : EmployeeState()

    data class OnFetchEmployee(
        val employee: Employee
    ) : EmployeeState()

    data class OnLoading(
        val isLoading: Boolean
    ) : EmployeeState()

}

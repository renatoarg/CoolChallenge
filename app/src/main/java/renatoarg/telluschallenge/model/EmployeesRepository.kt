package renatoarg.telluschallenge.model

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeesRepository @Inject constructor(private val employeesApi: EmployeesApi) {

    var isRefresh: Boolean = true

    suspend fun getEmployees(): Response<EmployeesResponse> {
        isRefresh = false
        return employeesApi.getEmployees()
    }

    @Deprecated("Use employees list. It already contains all employees. Avoid too many requests!!")
    suspend fun getEmployee(id: Long) = employeesApi.getEmployee(id)

}
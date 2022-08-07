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

}
package renatoarg.telluschallenge.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeesApi {

    @GET("employees")
    suspend fun getEmployees(): Response<EmployeesResponse>

    @Deprecated("Use employees list, avoid too many requests!")
    @GET("employee/{id}")
    suspend fun getEmployee(
        @Path("id") id: Long
    ): Response<EmployeeResponse>
}
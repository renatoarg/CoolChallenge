package renatoarg.telluschallenge.model

import retrofit2.Response
import retrofit2.http.GET

interface EmployeesApi {

    @GET("employees")
    suspend fun getEmployees(): Response<EmployeesResponse>

}
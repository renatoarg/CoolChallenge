package renatoarg.telluschallenge.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeesRepository @Inject constructor(private val employeesApi: EmployeesApi) {

    suspend fun getEmployees() = employeesApi.getEmployees()

}
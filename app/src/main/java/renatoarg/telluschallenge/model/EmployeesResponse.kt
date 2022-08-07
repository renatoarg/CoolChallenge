package renatoarg.telluschallenge.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeesResponse(
    val status: String = "",
    val message: String = "",
    val data: List<Employee> = emptyList()
) : Parcelable
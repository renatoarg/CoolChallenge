package renatoarg.telluschallenge.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeesResponse(
    override val status: String = "",
    override val message: String = "",
    val data: List<Employee> = emptyList()
) : EmployeesResponseWrapper(), Parcelable
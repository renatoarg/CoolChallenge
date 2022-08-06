package renatoarg.telluschallenge.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeResponse(
    override val status: String = "",
    override val message: String = "",
    val data: Employee? = null
) : EmployeesResponseWrapper(), Parcelable
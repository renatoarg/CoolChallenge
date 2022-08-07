package renatoarg.telluschallenge.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeResponse(
    val status: String = "",
    val message: String = "",
    val data: Employee
) : Parcelable
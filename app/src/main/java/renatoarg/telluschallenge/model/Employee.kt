package renatoarg.telluschallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    val id: Long,
    val employee_name: String,
    val employee_salary: Long,
    val employee_age: Int,
    val profile_image: String
) : Parcelable

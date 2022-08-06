package renatoarg.telluschallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    @SerializedName("id")
    val id: Long,
    @SerializedName("employee_name")
    val name: String,
    @SerializedName("employee_salary")
    val salary: Long,
    @SerializedName("employee_age")
    val age: Int,
    @SerializedName("profile_image")
    val image: String
) : Parcelable

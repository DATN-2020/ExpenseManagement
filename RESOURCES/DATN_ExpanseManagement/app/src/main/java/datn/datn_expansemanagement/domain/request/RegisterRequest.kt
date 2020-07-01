package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class RegisterRequest(
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("User_Name")
    val userName: String
)
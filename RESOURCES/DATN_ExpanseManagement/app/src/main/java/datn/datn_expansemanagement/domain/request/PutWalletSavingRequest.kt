package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class PutWalletSavingRequest(
    @SerializedName("id_saving")
    val idSaving: Int
)
package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName


data class NotifyRequest(
    @SerializedName("app_id")
    var appId: String,
    @SerializedName("contents")
    var contents: Contents,
    @SerializedName("headings")
    var headings: Headings,
    @SerializedName("include_player_ids")
    var includePlayerIds: List<String>
) {
    data class Contents(
        @SerializedName("en")
        var en: String
    )

    data class Headings(
        @SerializedName("en")
        var en: String
    )
}


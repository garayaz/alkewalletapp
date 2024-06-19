package araya.gonzalo.alkewallwt.model

import com.google.gson.annotations.SerializedName

sealed class DepositTransferResponse {
    data class Success(
        val amount: Long,
        val concept: String,
        val date: String,
        val type: String,
        val accountId: Int,
        val userId: Int,
        @SerializedName("to_account_id")
        val toAccountId: Int
    ) : DepositTransferResponse()

    data class Error(
        val error: String,
        val status: Int
    ) : DepositTransferResponse()
}

package araya.gonzalo.alkewallwt.model

import com.google.gson.annotations.SerializedName

data class DepositTransferRequest (

    val amount: Long,
    val concept: String,
    val date: String,
    val type: String,
    val accountId: Int,
    val userId: Int,
    @SerializedName(value = "to_account_id")
    val toAccountId: Int
)

package araya.gonzalo.alkewallwt.model

import com.google.gson.annotations.SerializedName

data class TransactionAW(

val id: Int?,
val amount: Long?,
val concept: String?,
val date: String?,
val type: String?,
val accountId: Long?,
val userId: Int?,
@SerializedName(value = "to_account_id")
val toAccountId: Long?,
val createdAt: String?,
val updatedAt: String?,
val error: String?,
val status: Long?
)
//    val amount: Long?,
//    val concept: String?,
//    val date: String?,
//    val type: String?,
//    val accountID: Long?,
//    val userID: Long?,
//    val toAccountID: Long?,
//    val error: String?,
//    val status: Long?
//)
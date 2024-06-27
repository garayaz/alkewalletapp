package araya.gonzalo.alkewallwt.model

import androidx.room.Entity
import androidx.room.PrimaryKey
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
val toAccountId: Int?,
val createdAt: String?,
val updatedAt: String?,
val error: String?,
val status: Long?
)
package araya.gonzalo.alkewallwt.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Se define la clase Transaction como una entidad de Room (tabla en la base de datos
@Entity(tableName = "Transactions_table")
data class Transaction(
    @PrimaryKey
    @ColumnInfo(name = "id")  val id: Int?,
    @ColumnInfo(name = "amount")  val amount: Long?,
    @ColumnInfo(name = "concept")  val concept: String?,
    @ColumnInfo(name = "date")  val date: String?,
    @ColumnInfo(name = "type")  val type: String?,
    @ColumnInfo(name = "accountId")  val accountId: Long?,
    @ColumnInfo(name = "userId")  val userId: Int?,
    @SerializedName(value = "to_account_id")
    @ColumnInfo(name = "toAccountId")  val toAccountId: Int?,
    @ColumnInfo(name = "createdAt")  val createdAt: String?,
    @ColumnInfo(name = "updatedAt")  val updatedAt: String?,
    @ColumnInfo(name = "error")  val error: String?,
    @ColumnInfo(name = "status")  val status: Long?
)

package araya.gonzalo.alkewallwt.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "created_users")
data class User(
val id : Int = 0,
val first_name: String,
val last_name: String,
val email: String,
val password: String,
val roleId: Long = 2,
val points: Long = 0

)
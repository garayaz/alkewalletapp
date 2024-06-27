package araya.gonzalo.alkewallwt.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "first_name") val first_name: String,
    @ColumnInfo(name = "last_name") val last_name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "role_id") val roleId: Long = 1,
    @ColumnInfo(name = "points") val points: Long = 0
)

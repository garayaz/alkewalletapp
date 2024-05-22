package araya.gonzalo.alkewallwt.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "created_users")
data class User(


val id : Int = 0,

@ColumnInfo(name = "firstName")
val first_name: String,

val last_name: String,
val email: String,
val password: String,
val roleId: Long = 2,
val points: Long = 0

)
/**
class AppConstant{
    companion object{
        val users = listOf(
            User("Gonzalo", "Araya", "garayaz@gmail.com","12345",0,0),
        )
    }
} **/
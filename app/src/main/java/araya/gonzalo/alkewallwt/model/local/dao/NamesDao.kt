package araya.gonzalo.alkewallwt.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User
// Se define el data access object, que contiene como interactuamos con la base de datos, las consultas a la base de datos
@Dao
interface NamesDao {

    @Query("SELECT * FROM user_table")
    suspend fun getUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUser(user: User)


}
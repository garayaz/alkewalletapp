package araya.gonzalo.alkewallwt.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User
import retrofit2.http.DELETE

// Se define el data access object, que contiene como interactuamos con la base de datos, las consultas a la base de datos
@Dao
interface TransactionsDao {

    // Consulta para obtener todas las transacciones desde la base de datos
    @Query("SELECT * FROM transactions_table")
    suspend fun getAllTransactions(): MutableList<Transaction>

    // Funcion que permite insertar transacciones en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: MutableList<Transaction>)

    // Funcion que permite eliminar transacciones de la base de datos
    @Query("DELETE FROM transactions_table")
    suspend fun deleteAllTransactions(): Int

    @Query("SELECT * FROM user_table")
    suspend fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

}
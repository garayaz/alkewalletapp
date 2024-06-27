package araya.gonzalo.alkewallwt.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User
import retrofit2.http.DELETE

@Dao
interface TransactionsDao {
    @Query("SELECT * FROM transactions_table")
    suspend fun getAllTransactions(): MutableList<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: MutableList<Transaction>)

    @Query("DELETE FROM transactions_table")
    suspend fun deleteAllTransactions(): Int

    @Query("SELECT * FROM user_table")
    suspend fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

}
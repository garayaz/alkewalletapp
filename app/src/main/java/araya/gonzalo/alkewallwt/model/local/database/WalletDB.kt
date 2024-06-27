package araya.gonzalo.alkewallwt.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import araya.gonzalo.alkewallwt.model.local.dao.NamesDao
import araya.gonzalo.alkewallwt.model.local.dao.TransactionsDao
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User

@Database(entities = [Transaction::class, User::class], version = 1,
exportSchema = false
)

abstract class WalletDB: RoomDatabase() {
    abstract fun getTransactionDao(): TransactionsDao
    abstract fun getUserDao(): NamesDao

    companion object {

        @Volatile
        private var INSTANCE: WalletDB? = null

        fun getDatabase(context: Context): WalletDB {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletDB::class.java,
                    "wallet_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}
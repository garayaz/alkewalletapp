package araya.gonzalo.alkewallwt.di

import android.content.Context
import androidx.room.Room
import araya.gonzalo.alkewallwt.model.local.database.WalletDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val WALLET_DB_NAME = "WalletDB"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WalletDB::class.java, WALLET_DB_NAME).build()

    @Singleton
    @Provides
    fun provideTransactionsDao(db: WalletDB) = db.getTransactionDao()

    @Singleton
    @Provides
    fun provideUserDao(db: WalletDB) = db.getUserDao()

}
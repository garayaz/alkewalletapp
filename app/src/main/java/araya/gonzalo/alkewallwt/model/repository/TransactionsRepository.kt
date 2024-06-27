package araya.gonzalo.alkewallwt.model.repository

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.DepositTransferResponseResp
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User
import retrofit2.Call

interface TransactionsRepository {

    //Para trabajar con la API
    suspend fun fetchTransactions(): DataObject
    suspend fun fetchTransactionsResponse(): Call<DepositTransferResponseResp>

    //Para trabajar con BD Local

    suspend fun saveAllTransactionsOnDB(transactions: MutableList<Transaction>)
    suspend fun getAllTransactionsFromDB(): MutableList<Transaction>

    suspend fun deleteAllTransactionsOnDB(): Int
    suspend fun saveUserOnDB(user: User)
    suspend fun getUserFromDB(idUser: Int): User

}
package araya.gonzalo.alkewallwt.model.repository

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.DepositTransferRequest
import araya.gonzalo.alkewallwt.model.DepositTransferResponseResp
import araya.gonzalo.alkewallwt.model.local.dao.TransactionsDao
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class TransactionsImp(
    private var apiservice: TransactionsService,
    private var daoDB: TransactionsDao
) : TransactionsRepository {
    val tokenpass = "Bearer $token"
    override suspend fun fetchTransactions(): DataObject {
        return withContext(Dispatchers.IO) {
            val response =
                apiservice.getUserTrans(token = tokenpass) // esta es una funcion lambda por lo
            // que se requiere devolver un valor ACA ESTA el ERROR

            response
        }
    }

    override suspend fun fetchTransactionsResponse(): Call<DepositTransferResponseResp> {
        return withContext(Dispatchers.IO) {
            apiservice.addTransaction(
                token = tokenpass,
                DepositTransferRequest(
                    100, "xxx",
                    "22/01/09", "payment", 2172,
                    3295, 2172
                )
            )
        }
    }

    override suspend fun saveAllTransactionsOnDB(transactions: MutableList<Transaction>) {
        return withContext(Dispatchers.IO) {
            daoDB.insertTransactions(transactions)
        }
    }

    override suspend fun getAllTransactionsFromDB(): MutableList<Transaction> {
        return withContext(Dispatchers.IO) {
            val response = daoDB.getAllTransactions()
            response
        }
    }

    override suspend fun deleteAllTransactionsOnDB(): Int {
        return daoDB
            .deleteAllTransactions()
    }

    override suspend fun saveUserOnDB(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserFromDB(idUser: Int): User {
        TODO("Not yet implemented")
    }
}
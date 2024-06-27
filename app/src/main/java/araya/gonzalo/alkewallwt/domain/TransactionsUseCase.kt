package araya.gonzalo.alkewallwt.domain

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.DepositTransferResponseResp
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import araya.gonzalo.alkewallwt.model.local.entity.User
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import retrofit2.Call

class TransactionsUseCase(private val repository: TransactionsImp) {

    suspend fun getAllTransactions(): DataObject {
        return repository.fetchTransactions()
    }

    suspend fun getAllTransactionResponse(): Call<DepositTransferResponseResp> {
        return repository.fetchTransactionsResponse()
    }

    suspend fun saveAllTransactionsOnDB(transactions: MutableList<Transaction>) {
        return repository.saveAllTransactionsOnDB(transactions)
    }

    suspend fun deleteAllTransactionsOnDB(): Int {
        return repository.deleteAllTransactionsOnDB()
    }
    suspend fun saveUserOnDB(user: User) {
        return repository.saveUserOnDB(user)
    }

    suspend fun getAllTransactionsFromDB(): MutableList<Transaction> {
        return repository.getAllTransactionsFromDB()
    }

}
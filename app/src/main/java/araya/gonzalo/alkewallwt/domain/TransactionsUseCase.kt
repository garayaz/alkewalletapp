package araya.gonzalo.alkewallwt.domain

import android.util.Log
import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import retrofit2.Call

class TransactionsUseCase(private val repository: TransactionsImp) {

    suspend fun getAllTransactions(): DataObject {
        Log.i("UseCase", "getAllTransactions")
        return repository.fetchTransactions()
    }

    suspend fun getAllTransactionResponse(): Call<TransactionAW> {
        Log.i("UseCase", "getAllTransactions")
        return repository.fetchTransactionsResponse()
    }
}
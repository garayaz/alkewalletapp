package araya.gonzalo.alkewallwt.domain

import android.util.Log
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp

class TransactionsUseCase(private val repository: TransactionsImp) {

    suspend fun getAllTransactions(): MutableList<TransactionsResponse> {
        return repository.fetchTransactions()
    }
}
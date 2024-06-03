package araya.gonzalo.alkewallwt.model.repository

import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import retrofit2.Call

interface TransactionsRepository {

    suspend fun fetchTransactions(): MutableList<TransactionsResponse>
}
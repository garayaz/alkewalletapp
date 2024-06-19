package araya.gonzalo.alkewallwt.model.repository

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.DepositTransferResponse
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import retrofit2.Call

interface TransactionsRepository {

    suspend fun fetchTransactions(): DataObject
    suspend fun fetchTransactionsResponse(): DepositTransferResponse

}
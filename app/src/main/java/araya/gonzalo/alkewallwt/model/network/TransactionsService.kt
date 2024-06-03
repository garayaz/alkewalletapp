package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TransactionsService {
    @GET("transactions")
    suspend fun getUserTrans(
        @Header("Authorization") token: String
    ): DataObject

// ): MutableList<TransactionsResponse>
}
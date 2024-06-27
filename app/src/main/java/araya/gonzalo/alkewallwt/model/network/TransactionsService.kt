package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.DepositTransferRequest
import araya.gonzalo.alkewallwt.model.DepositTransferResponseResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionsService {

    // Servicio que trae las transacciones del usuario logeado
    @GET("transactions")
    suspend fun getUserTrans(
        @Header("Authorization") token: String
    ): DataObject

    @POST("transactions")
   fun addTransaction(
        @Header("Authorization") token: String,
        @Body transaction: DepositTransferRequest
    ): Call<DepositTransferResponseResp>
}
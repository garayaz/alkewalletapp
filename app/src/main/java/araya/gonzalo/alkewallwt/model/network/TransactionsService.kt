package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.DepositTransferRequest
import araya.gonzalo.alkewallwt.model.DepositTransferResponse
import araya.gonzalo.alkewallwt.model.LoginRequest
import araya.gonzalo.alkewallwt.model.LoginResponse
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionRequest
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.User
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
    suspend fun addTransaction(
        @Header("Authorization") token: String,
        @Body transaction: DepositTransferRequest
    ): DepositTransferResponse
}
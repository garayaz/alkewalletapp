package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp

class TransactionsApiClient {
    val tokenpass = "Bearer ${AlkeWalletApp.token}"
    private val retrofit = RetrofitClass.retrofitobj // se llama al retrofit para conectarme
    // ahora como me conecto a los servicios
    suspend fun getTransactions():MutableList<TransactionsResponse> {
        val response = retrofit.create(TransactionsService::class.java).getUserTrans(tokenpass) // se llama al
        // servicio TransactionsService al metodo getUserTrans
        return response
    }
}
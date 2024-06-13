package araya.gonzalo.alkewallwt.model.repository

import android.util.Log
import araya.gonzalo.alkewallwt.model.AccountRequest
import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionRequest
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class TransactionsImp(private var apiservice: TransactionsService): TransactionsRepository {
    val tokenpass = "Bearer $token"
    override suspend fun fetchTransactions(): DataObject {
        return withContext(Dispatchers.IO){
            Log.i("IMP - fetchTransactions token:", tokenpass.toString())
            val response = apiservice.getUserTrans(token=tokenpass) // esta es una funcion lambda por lo
            // que se requiere devolver un valor ACA ESTA el ERROR

            response
        }
    }

    override suspend fun fetchTransactionsResponse(): Call<TransactionAW> {
        return withContext(Dispatchers.IO) {
            val response = apiservice.addTransaction(TransactionRequest(100, "xxx", "22/01/09", "payment", 123, 1000, 230)) // esta es una funcion lambda por lo
            // que se requiere devolver un valor
            response
        }
    }
    /**    val tokenpass = "Bearer $token"
    override suspend fun fetchTransactions(): MutableList<TransactionAW>{
    return withContext(Dispatchers.IO){
    // se declara un hilo para que se ejecute en ese hilo de
    // entrada y salida.
    // ahora me conecto a la capa anterior para obtener los datos usando getVideoGames de la api
    // "VideoGameApiClient" que es la que se conecta al servicio videoGamesService, para eso paso
    // el parametro apiservice: videoGameService
    // Ahora puedo usar el apiservice
    val response = apiservice.getUserTrans(tokenpass) // esta es una funcion lambda por lo
    // que se requiere devolver un valor ACA ESTA el ERROR
    response
    }
    }
    // aca despues iran los fetch a base de datos o a otros servicios **/

}
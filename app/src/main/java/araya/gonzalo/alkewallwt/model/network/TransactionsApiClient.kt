package araya.gonzalo.alkewallwt.model.network

import android.util.Log
import araya.gonzalo.alkewallwt.config.CustomTypeAdapterFactory
import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TransactionsApiClient {
    val tokenpass = "Bearer ${AlkeWalletApp.token}"
    private val retrofit = RetrofitClass.retrofitobj // se llama al retrofit para conectarme
    // ahora como me conecto a los servicios

    suspend fun getTransactions(): DataObject {

        Log.i("TransactionsApiClient", "getTransactions")
        val response = retrofit.create(TransactionsService::class.java).getUserTrans(tokenpass) // se llama al
        // servicio TransactionsService al metodo getUserTrans
        // Parse the JSON response using Gson or another parsing library
        return response
    }
}
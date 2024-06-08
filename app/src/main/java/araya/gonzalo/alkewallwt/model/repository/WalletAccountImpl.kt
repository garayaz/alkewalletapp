package araya.gonzalo.alkewallwt.model.repository

import android.util.Log
import araya.gonzalo.alkewallwt.model.AccountRequest
import araya.gonzalo.alkewallwt.model.AccountResponse
import araya.gonzalo.alkewallwt.model.network.AlkeWalletAccountService
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class WalletAccountImpl(private var apiservice: AlkeWalletAccountService) :
    WalletAccountRepository {
    override suspend fun fetchAwAccounts(): Call<AccountResponse> {
        val tokenpass = "Bearer ${AlkeWalletApp.token}"
        return withContext(Dispatchers.IO) {
            Log.i("Wallet - fetchTransactions token:", tokenpass.toString())
            val response = apiservice.newWalletAccount(tokenpass, AccountRequest("12/04/2024", 10000, false, 100)) // esta es una funcion lambda por lo
            // que se requiere devolver un valor
            response
        }
    }
}
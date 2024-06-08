package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.AccountRequest
import araya.gonzalo.alkewallwt.model.AccountResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AlkeWalletAccountService {
    @POST("accounts")
    fun newWalletAccount(
        @Header("Authorization") token: String,
        @Body createdAccount: AccountRequest
    ): Call<AccountResponse>


}
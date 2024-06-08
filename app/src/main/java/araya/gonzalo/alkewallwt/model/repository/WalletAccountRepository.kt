package araya.gonzalo.alkewallwt.model.repository

import araya.gonzalo.alkewallwt.model.AccountResponse
import retrofit2.Call


interface WalletAccountRepository {
    suspend fun fetchAwAccounts(): Call<AccountResponse>
}
package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AccountService {
    @GET("auth/me")
    fun obtenerInfoLogin(
        @Header("Authorization") token: String
    ): Call<User>
}
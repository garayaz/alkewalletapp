package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.RegisterRequest
import araya.gonzalo.alkewallwt.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NewAccountService {

    @POST("users")

    // @body lo que enviamos y en Call lo que vamos a recibir
    fun createUserAccount(
        @Body createdUser: RegisterRequest
    ): Call<RegisterResponse>

}
package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.LoginRequest
import araya.gonzalo.alkewallwt.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// API Service que contiene los metodos para logear al usuario
interface LoginService {
    //servicio que hace login y recibe parametros email y password encapsulado en
    // un objeto
    @POST("auth/login")
    fun hacerlogin(
        @Body request: LoginRequest
    ): Call<LoginResponse>

}
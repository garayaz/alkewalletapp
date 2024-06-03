package araya.gonzalo.alkewallwt.model.network

import araya.gonzalo.alkewallwt.model.LoginRequest
import araya.gonzalo.alkewallwt.model.LoginResponse
import araya.gonzalo.alkewallwt.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// API Service que contiene los metodos para logear al usuario
interface LoginService {
    //servicio que hace login y recibe parametros email y password encapsulado en
    // ("@Body request: LoginRequest" esta parte define lo que envio) un objeto (LoginRequest que
    // es el modelo de datos definido)
    //(": Call <LoginResponse>" esta define lo que recibo) y devuelve un objeto (LoginResponse)
    @POST("auth/login")
    fun doLogin(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    // servicio que obtiene los datos del usuario logeado, recibe como parametro el token con la
    // palabra Bearer
    @GET("auth/me")
    fun getUserInfo(
        @Header("Authorization") token: String
    ): Call<User>

}
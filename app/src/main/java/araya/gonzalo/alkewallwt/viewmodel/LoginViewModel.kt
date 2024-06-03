package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import araya.gonzalo.alkewallwt.model.LoginRequest
import araya.gonzalo.alkewallwt.model.LoginResponse
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.User
import araya.gonzalo.alkewallwt.model.network.LoginService
import araya.gonzalo.alkewallwt.model.network.NewAccountService
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * View model encargado de hacer el Login de la app
 */
class LoginViewModel : ViewModel() {
    // este viewModel deberia conectarse a una api para hacer el login, enviando
    // el email y la contrasena
    //Variable LiveData que va a informar a la vista el login, entrega un resultado booleano
    // val loginResultLiveData = MutableLiveData<Boolean>()
    val tokenResultLiveData = MutableLiveData<String?>() // almacena el token devuelto por la API
    val errorResultLiveData = MutableLiveData<String?>() // almacena el error devuelto por la API
    val usuarioLiveData = MutableLiveData<User?>() // almacena el usuario logueado
    val transactionsLiveData = MutableLiveData<MutableList<TransactionAW>?>() // almacena las
    // transacciones del usuario logueado
    /**
     * funcion que implementa una corrrutina para llamar en el futuro a la Api
     */
    fun doLogin(email: String, contrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Aca nosotros vamos a llamar a la API
                // esta es la instancia de retrofit que vamos a usar para hacer el login
                val login: LoginService = RetrofitClass.retrofitobj.create(LoginService::class.java)
                // se crea una variable con lo que va a responder la API, callingAPI del tipo Call
                // eso es lo que responder segun esta definido en la API
                var callingApi: Call<LoginResponse> =
                login.doLogin(
                    // LoginReques es el modelo que vamos a enviar a la API
                    LoginRequest(email, contrasena)
                )
                //aca hacemos la llamada al servicio y le pasamos el callback para obtener la
                // respuesta de la API, que me puede devolver un ok "onResponse" o un
                // error "onFailure"
                callingApi.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val resp: LoginResponse? = response.body()
                            if (resp?.accessToken != null) {
                                tokenResultLiveData.postValue(resp
                                    .accessToken)
                            } else {
                                tokenResultLiveData.postValue(null)
                                errorResultLiveData.postValue(resp?.error)
                            }

                        } else {
                            val errorResp: LoginResponse? = response.body()
                            tokenResultLiveData.postValue(null)
                            errorResultLiveData.postValue(errorResp?.error)
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        tokenResultLiveData.postValue(null)
                    }


                })
            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                tokenResultLiveData.postValue(null)
                errorResultLiveData.postValue(null)
            }
        }
    }
    // se crea funcion para obtener la información del usuario logueado
    fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val getUsuario = RetrofitClass.retrofitobj.create(LoginService::class.java)
                val tokenpass = "Bearer $token"
                val calluser: Call<User> = getUsuario.getUserInfo(tokenpass)
                calluser.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful){
                            val usuarioLogin = response.body()
                            usuarioLiveData.postValue(usuarioLogin)
                        }else{
                            usuarioLiveData.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        usuarioLiveData.postValue(null)
                    }
                })
            } catch (e: Exception) {
                usuarioLiveData.postValue(null)
            }
        }
    }

}
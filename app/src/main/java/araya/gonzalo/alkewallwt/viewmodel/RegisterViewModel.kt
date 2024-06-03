package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import araya.gonzalo.alkewallwt.model.RegisterRequest
import araya.gonzalo.alkewallwt.model.RegisterResponse
import araya.gonzalo.alkewallwt.model.network.NewAccountService
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    //Este viewModel deberia conectarse con una api para hacer el registro del nuevo usuario

    val RegistrationValid = MutableLiveData<Boolean>()

    //Se hacen las validaciones de los datos ingresados por el usuario en la funcion "hacerRegistro"
    fun userRegister(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        repassword: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar a la API
                if ((firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) && password == repassword) {
                    //       RegistrationValid.postValue(true)
                    val createUser = RetrofitClass.retrofitobj.create(NewAccountService::class.java)
                    val createCall = createUser.createUserAccount(
                        RegisterRequest(firstName, lastName, email, password, 1, 500)
                    )
                    //Ahora se llama a la API para hacer el registro
                    createCall.enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            if (response.isSuccessful) {
                                RegistrationValid.postValue(true)
                            } else {
                                RegistrationValid.postValue(false)
                            }
// ACA fqlta algo ver parentesis
                        }

                        override fun onFailure(p0: Call<RegisterResponse>, p1: Throwable) {
                            RegistrationValid.postValue(false)
                        }
                    })
                } else {
                    RegistrationValid.postValue(false)
                }

            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                RegistrationValid.postValue(false)
            }
        }
    }
}






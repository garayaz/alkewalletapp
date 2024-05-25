package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    //Este viewModel deberia conectarse con una api para hacer el registro del nuevo usuario

    val RegistrationValid = MutableLiveData<Boolean>()

//Se hacen las validaciones de los datos ingresados por el usuario en la funcion "hacerRegistro"
    fun hacerRegistro(firstName: String, lastName: String, email: String, password: String, repassword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar a la API
                if ((firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) && password == repassword) {
                    RegistrationValid.postValue(true)

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






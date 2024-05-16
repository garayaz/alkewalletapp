package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** Este es el encargado de hacer el login a la app **/
class LoginViewModel : ViewModel() {

    val loginResultLiveData = MutableLiveData<Boolean>()

    fun hacerlogin(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //val response = ApiClient.apiService.login(username, password)
                //if (response.isSuccessful) {
                //    val jsonResponse = response.body()
                //   if (jsonResponse != null) {
                //       val status = jsonResponse.getString("status")
                //       val message = jsonResponse.getString("message")
                //      loginResultLiveData.postValue("Status: $status\nMessage: $message")
                //  } else {
                if (email == "garayaz@gmail.com" && password == "1234") {
                    loginResultLiveData.postValue(true)
                } else {

                }

                //  }
                // } else {
                //    loginResultLiveData.postValue("Error: ${response.message()}")
                //  }
            } catch (e: Exception) {
                // loginResultLiveData.postValue("Error: ${e.message}")
            }
        }
    }
}
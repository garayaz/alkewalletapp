package araya.gonzalo.alkewallwt.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import araya.gonzalo.alkewallwt.model.AccountRequest
import araya.gonzalo.alkewallwt.model.AccountResponse
import araya.gonzalo.alkewallwt.model.RegisterResponse
import araya.gonzalo.alkewallwt.model.network.AlkeWalletAccountService
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class NewAccountViewModel: ViewModel() {
    val newAccountValid = MutableLiveData<Boolean>()
    val tokenpass = "Bearer ${AlkeWalletApp.token}"


    fun newAccountAW(    creationDate : String,
                         money : Int,
                         isBlocked : Boolean,
                         userId : Int) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val createAccount = RetrofitClass.retrofitobj.create(AlkeWalletAccountService::class.java)
            val createCall = createAccount.newWalletAccount(
                token=tokenpass, AccountRequest(creationDate, money, isBlocked, userId)
            )
            //Ahora se llama a la API para hacer el registro
            createCall.enqueue(object : Callback<AccountResponse> {
                override fun onResponse(call: Call<AccountResponse>, response: Response<AccountResponse>) {
                    if (response.isSuccessful) {
                        AlkeWalletApp.createdAwAccount = response.body()?.id
                        Log.i("CUENTA CREADA", "onResponse: ${response.body()?.id}")
                        newAccountValid.postValue(true)
                    } else {
                        newAccountValid.postValue(false)
                    }
                }

                override fun onFailure(call: Call<AccountResponse>, response: Throwable) {
                    newAccountValid.postValue(false)
                }


            })

            } catch (e: Exception) {
                e.printStackTrace()
        }
        }
    }

}
package araya.gonzalo.alkewallwt.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.DepositTransferRequest
import araya.gonzalo.alkewallwt.model.DepositTransferResponseResp
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime.now

//class RequestViewModel(private val useCase: TransactionsUseCase) : ViewModel(){
class RequestViewModel(private val useCase: TransactionsUseCase) : ViewModel(){
    private val _transactionResult = MutableLiveData<Boolean>()
    val transactionResult: LiveData<Boolean> get() = _transactionResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    @RequiresApi(Build.VERSION_CODES.O)
    fun depositarOtransferir(
        token: String,
        account: Int,
        user: Int,
        type: String,
        concept: String,
        amount: Long
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
             //   val getTransaction = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
                Log.i("RequestVM", "En TRY createTrans")
              //  val createTrans = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
                Log.i("RequestVM", "En TRY depositTransfReq")
                val depositTransferRequest = DepositTransferRequest(
                    amount = amount,
                    concept = concept,
                    date = now().toString(), // Function to get formatted date string
                    type = type,
                    accountId = account,
                    userId = user,
                    toAccountId = account // Assuming 'toAccountId' is the same as 'account'
                )
                val createRequest = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
                val createTrans = createRequest.addTransaction(token, depositTransferRequest)
                //Ahora se llama a la API para hacer el registro
                createTrans.enqueue(object : Callback<DepositTransferResponseResp> {
                    override fun onResponse(
                        call: Call<DepositTransferResponseResp>,
                        response: Response<DepositTransferResponseResp>
                    ) {
                        if (response.isSuccessful) {
                            val transactionData = response.body()
                            _transactionResult.postValue(true)
                        } else {
                            _transactionResult.postValue(false)
                        }
                    }

                    override fun onFailure(p0: Call<DepositTransferResponseResp>, p1: Throwable) {
                        _transactionResult.postValue(false)
                    }

                })

                /**               val response = createTrans.addTransaction(
                    token,
                    depositTransferRequest
                )
                when (response) {
                    is DepositTransferResponse.Success -> {
                        Log.d("when", "RECEPCION EXITOSA")
                        val transactionData = response
                        _transactionResult.postValue(true)
                    }

                    is DepositTransferResponse.Error -> {
                        // Handle the error
                        _transactionResult.postValue(false)
                        //val errorMessage = response.error
                        //val errorCode = response.status
                        // ... display error message to the user or take other actions ...
                    }
                } **/
            } catch (e: Exception) {
                _transactionResult.postValue(false)
                Log.e(
                    "SendViewModelCatch",
                    "Error al Recibir Dinero", e
                )
            }
        }

    }
}
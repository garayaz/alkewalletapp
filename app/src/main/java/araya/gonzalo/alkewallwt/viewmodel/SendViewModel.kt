package araya.gonzalo.alkewallwt.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class SendViewModel(private val useCase: TransactionsUseCase) : ViewModel() {

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
                val depositTransferRequest = DepositTransferRequest(
                    amount = amount,
                    concept = concept,
                    date = now().toString(), // Funcion para formatear la fecha
                    type = type,
                    accountId = account,
                    userId = user,
                    toAccountId = account
                )
                val createRequest = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
                val createTrans = createRequest.addTransaction(token, depositTransferRequest)
                //Ahora se llama a la API para hacer el registro,
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
            } catch (e: Exception) {
                _transactionResult.postValue(false)
            }
        }

    }
}
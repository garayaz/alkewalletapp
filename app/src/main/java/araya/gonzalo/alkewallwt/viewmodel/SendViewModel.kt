package araya.gonzalo.alkewallwt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.DepositTransferRequest
import araya.gonzalo.alkewallwt.model.DepositTransferResponse
import araya.gonzalo.alkewallwt.model.RegisterRequest
import araya.gonzalo.alkewallwt.model.network.NewAccountService
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.createdAwAccount
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.loggedUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class SendViewModel(private val useCase: TransactionsUseCase) : ViewModel() {

    private val _transactionResult = MutableLiveData<Boolean>()
    val transactionResult: LiveData<Boolean> get() = _transactionResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

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
                val createTrans = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
                val depositTransferRequest = DepositTransferRequest(
                    amount = amount,
                    concept = concept,
                    date = LocalDateTime.now().toString(), // Function to get formatted date string
                    type = type,
                    accountId = account,
                    userId = user,
                    toAccountId = account // Assuming 'toAccountId' is the same as 'account'
                )
                val response = createTrans.addTransaction(
                    token,
                    depositTransferRequest
                )
                when (response) {
                    is DepositTransferResponse.Success -> {
                        Log.d("when", "ENVIO EXITOSO")
                        val transactionData = response
                    }

                    is DepositTransferResponse.Error -> {
                        // Handle the error
                        val errorMessage = response.error
                        val errorCode = response.status
                        // ... display error message to the user or take other actions ...
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "SendViewModelCatch",
                    "Error al depositar", e
                )
            }
        }

    }
}
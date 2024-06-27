package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: TransactionsUseCase) : ViewModel() {

    val tokenpass = "Bearer ${AlkeWalletApp.token}"
    private var _transactionList = MutableLiveData<MutableList<Transaction>>()
    val transactionsLV
        get() = _transactionList


    fun getAllTransactionsFromServer() {
        viewModelScope.launch {
            try {
                // lo primero es ir a internet y traer los datos si hay conexion a internet
                val response =
                    useCase.getAllTransactions().data //servicio que trae datos del server
                if (response.isNotEmpty()) {
                    try {
                        val eliminados = useCase.deleteAllTransactionsOnDB()
                    } catch (e: Exception) {
                    }
                    useCase.saveAllTransactionsOnDB(response) //se guarda en la base de datos
                }
                _transactionList.value = response
            } catch (e: Exception) {
                _transactionList.value = useCase.getAllTransactionsFromDB()
            }

        }
    }
}
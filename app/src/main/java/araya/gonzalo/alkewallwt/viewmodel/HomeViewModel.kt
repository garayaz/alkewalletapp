package araya.gonzalo.alkewallwt.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.local.entity.Transaction
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: TransactionsUseCase) : ViewModel() {
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
                        Log.i("Limpia BD", "Se eliminaron: $eliminados registros")
                    } catch (e: Exception) {
                        Log.i("Limpia BD", "No se eliminaron registros")
                    }
                    useCase.saveAllTransactionsOnDB(response) //se guarda en la base de datos local
                }
                _transactionList.value = response
            } catch (e: Exception) {
                _transactionList.value = useCase.getAllTransactionsFromDB() // si no hay conexion a
            // internet se trae los datos de la base de datos local
            }

        }
    }
}
package araya.gonzalo.alkewallwt.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.User
import kotlinx.coroutines.launch
import retrofit2.Call

class HomeViewModel(private val useCase: TransactionsUseCase) : ViewModel() {
    // este viewmodel deberia traer las transacciones desde algun repositorio externo
// se define una variable Mutable, es decir que se puede modificar
    val tokenpass = "Bearer ${AlkeWalletApp.token}"
    private var _transactionList = MutableLiveData<DataObject>()
    // LiveData<MutableList<Transaction>> esta es la que se conecta con el observador, el mesero
    // languagesLV es el que se va a invocar desde el MainActivity, el lleva la data

    // se declara la variable transactionsLV que es un LiveData<MutableList<Transaction>>,
    // es decir es un dato observable y que comunicará a la vista los cambios que ocurran


    val transactionsLV
        get() = _transactionList

    // al llamar esta funcion (fetchTransactions()), esto es lo primero que se ejecuta, es decir,
    // se le pasan los datos a _languageList y esa se le pasa despues al "mesero" que es
    // transactionsLV, que escucha los cambios en la data
    init {
        viewModelScope.launch {
            _transactionList.value = useCase.getAllTransactions()
        }
    }

    //  VIEWMODEL "TIENE" QUE IR ACOMPAÑADO DE UN LIVEDATA, ASI COMO UN ADAPTADOR NO PUEDE
    //  EXISTIR SIN UN VIEWHOLDER
    // vamos a simular que desde el viewmodel nos conectamos a una fuente de datos
    //  diciendo traeme las transacciones, a traves de la siguiente funcion, esto se deberia hacer
    // con patron repository
}
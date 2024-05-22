package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.model.Transaction

class HomeViewModel: ViewModel() {


    private var _transactionList = MutableLiveData<MutableList<Transaction>>()
// mutable livedata es un tipo de livedata que se puede modificar y es el que se comunica con el mesero(observador)
    // mutablelist es una lista que se puede modificar
    // vamos a pasar los datos del livedata (_languageList) a la nueva variable que no es mutable
    // LiveData<MutableList<Language>> esta es la que se conecta con el observador, el mesero
    // languagesLV es el que se va a invocar desde el MainActivity, el lleva la data
    val transactionsLV: LiveData<MutableList<Transaction>>
        get() = _transactionList
    // al llamar esta funcion, esto es lo primero que se ejecuta, es decir, se le pasan los datos a
    // _languageList y esa se le pasa despues al mesero que es languagesLV

    init {
        fetchTransactions()
    }

    //  VIEWMODEL "TIENE" QUE IR ACOMPAÑADO DE UN LIVEDATA, ASI COMO UN ADAPTADOR NO PUEDE EXISTIR SIN UN VIEWHOLDER
    // vamos a simular que desde el viewmodel nos conectamos a una fuente de datos
    //  diciendo traeme los lenguajes, a traves de la siguiente funcion, esto se deberia hacer
    // con patron repository
    fun fetchTransactions() {
        val trList = mutableListOf(
            Transaction(R.drawable.photo1, "Kate Willow", "30 Dic. 00:12", 0, 1000000),
            Transaction(R.drawable.photo2, "Beyonce Collao", "31 Dic. 10:23", 0, 850000),
            Transaction(R.drawable.photo3, "Brad Brito", "01 Ene. 13:45", 0, 300000),
            Transaction(R.drawable.photo4, "Amy Tinto", "01 Ene. 14:25", 1, 120000),
            Transaction(R.drawable.photo5, "Cindy Lopez", "01 Ene. 20:23", 1, 300000),
            Transaction(R.drawable.photo3, "Brad Brito","03 Ene. 09:16", 1, 450000),
            Transaction(R.drawable.photo1, "Kate Willow", "03 Ene. 15:47", 0, 105000),
            Transaction(R.drawable.photo2, "Beyonce Collao", "03 Ene. 17:03", 0, 10000),
            Transaction(R.drawable.photo3, "Brad Brito", "03 Ene. 18:44", 1, 155000),
            Transaction(R.drawable.photo1, "Kate Willow", "03 Ene. 22:56", 1, 230000),
            Transaction(R.drawable.photo4, "Amy Tinto", "04 Ene. 19:32", 1, 90000),
            Transaction(R.drawable.photo1, "Kate Willow", "04 Ene. 00:31", 0, 30000),
            Transaction(R.drawable.photo1, "Kate Willow", "05 Ene. 18:44", 1, 540000)        )
        // Le paso los datos, la lista a la variable mutable list, los hago vivos
        _transactionList.value = trList

    }
}
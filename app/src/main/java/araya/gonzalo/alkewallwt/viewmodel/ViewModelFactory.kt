package araya.gonzalo.alkewallwt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase

class ViewModelFactory(private val transactionsUseCase: TransactionsUseCase) : ViewModelProvider.Factory {
    // esta es una funcion de extension factory
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(transactionsUseCase) as T
        }
        throw IllegalArgumentException("ViewModel no encontrado")
    }
}
package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import araya.gonzalo.alkewallwt.databinding.ActivityHomePageBinding
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.local.database.WalletDB
import araya.gonzalo.alkewallwt.model.network.RetrofitClass.Companion.retrofitobj
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import araya.gonzalo.alkewallwt.ui.adapter.TransactionsViewAdapter
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.awBalance
//import araya.gonzalo.alkewallwt.ui.adapter.TransactionsViewAdapter
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.loggedUser
import araya.gonzalo.alkewallwt.viewmodel.HomeViewModel
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory

class HomePageActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding
    val tokenpass = "Bearer ${AlkeWalletApp.token}"
    var totalAmount = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // lleno los datos del encabezado del Home
        binding.hpHola.text = "Hola, ${loggedUser?.first_name}!"
        val apiService = retrofitobj.create(TransactionsService::class.java)
        val dataBase = WalletDB.getDatabase(application)
        // a traves del parametro apiService conecto el repositorio con su capa anterior
        val repository = TransactionsImp(apiService, dataBase.getTransactionDao())
        // ahora la capa usecase se conecta con el repositorio por medio del parametro repository
        val useCase = TransactionsUseCase(repository)
        // view model no deja instanciar como los anteriores
        var adapterLg = TransactionsViewAdapter()
        binding.transactions.adapter = adapterLg
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.getAllTransactionsFromServer()
        viewModel.transactionsLV.observe(this) {
            binding.transactions.layoutManager = LinearLayoutManager(this)
            for (transaction in it) {
                if (transaction.type == "topup") {
                totalAmount += transaction.amount!!
            } else {
                totalAmount -= transaction.amount!!
            }
                }
            binding.hpTotal.text = totalAmount.toString()
            awBalance = totalAmount
            adapterLg.transactions = it //
            adapterLg.onItemClickistener = { transaction ->
                if (adapterLg.transactions.isEmpty()) {
                    Toast.makeText(this, "No hay transacciones", Toast.LENGTH_SHORT).show()
                    //binding.empty.visibility = View.VISIBLE
                } else {
                    //binding.empty.visibility = View.GONE
                }
            }
        }
        val botonverde = binding.hpButtonVerde
        botonverde.setOnClickListener()
        {
            if (totalAmount <= 0) {
                Toast.makeText(this, "No tienes fondos suficientes, favor cargar tu Wallet antes de utilizarla", Toast.LENGTH_SHORT).show()
            } else {
                val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java)
                startActivity(abrirPantallaSM)
            }

        }
        val botonazul = binding.hpButtonAzul
        botonazul.setOnClickListener()
        {
            val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java)
            startActivity(abrirPantallaRM)
        }
        val foto = binding.imgHpeFoto
        foto.setOnClickListener()
        {
            val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
            startActivity(abrirPerfil)
        }
    }
}


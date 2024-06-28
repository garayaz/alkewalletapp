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
    var totalAmount = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // lleno los datos del encabezado del Home
        binding.hpHola.text = "Hola, ${loggedUser?.first_name}!"
        val dataBase = WalletDB.getDatabase(application)
        val apiService = retrofitobj.create(TransactionsService::class.java)
        // a traves del parametro apiService conecto el repositorio con su capa anterior
        val repository = TransactionsImp(apiService, dataBase.getTransactionDao())
        // ahora la capa usecase se conecta con el repositorio por medio del parametro repository
        val useCase = TransactionsUseCase(repository)
        // Se crea una instancia del adapter para manejar del recyclerview
        var adapterLg = TransactionsViewAdapter()
        binding.transactions.adapter = adapterLg
        // Se crea el viwmodel usando factory para pasarle la usecase como dependencia, luego
        // se usa el factory para crear un instancia de HomeViewModel. este patron es usado para inyectar
        // dependencias (como el use case) en el viewmodel.
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.getAllTransactionsFromServer() // se llama al metodo que trae las transacciones
        viewModel.transactionsLV.observe(this) {
            binding.transactions.layoutManager = LinearLayoutManager(this)
            // se realiza calculo del total de fondos disponibles, sumando ingreso y restando egresos
            for (transaction in it) {
                if (transaction.type == "topup") {
                totalAmount += transaction.amount!!
            } else {
                totalAmount -= transaction.amount!!
            }
                }
            binding.hpTotal.text = totalAmount.toString()
            // se guarda el total de fondos en el companion object
            awBalance = totalAmount
            adapterLg.transactions = it //
        }
        // Se hace clickeable el boton de envio de dinero y se llama a la pantalla de envio de dinero
        val botonverde = binding.hpButtonVerde
        botonverde.setOnClickListener()
        {
            // validacion de fondos existentes
            if (totalAmount <= 0) {
                Toast.makeText(this, "No tienes fondos suficientes, favor cargar tu Wallet antes de utilizarla", Toast.LENGTH_SHORT).show()
            } else {
                val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java)
                startActivity(abrirPantallaSM)
            }

        }
        // Se hace clickeable el boton de solicitud de dinero y se llama a la pantalla de solicitud de dinero
        val botonazul = binding.hpButtonAzul
        botonazul.setOnClickListener()
        {
            val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java)
            startActivity(abrirPantallaRM)
        }
        // se hace clickeable la foto del perfil y se establece la apertura de la pantalla de perfil
        val foto = binding.imgHpeFoto
        foto.setOnClickListener()
        {
            val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
            startActivity(abrirPerfil)
        }
    }
}


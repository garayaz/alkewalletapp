package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.databinding.ActivityRequestMoneyBinding
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.local.database.WalletDB
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import araya.gonzalo.alkewallwt.viewmodel.RequestViewModel
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory3

class RequestMoneyActivity : AppCompatActivity() {
    lateinit var binding: ActivityRequestMoneyBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val tokenpass = "Bearer ${AlkeWalletApp.token}"
        val user = AlkeWalletApp.loggedUser!!.id
        super.onCreate(savedInstanceState)
        binding = ActivityRequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
        val dataBase = WalletDB.getDatabase(application)
        // a traves del parametro apiService conecto el repositorio con su caa anterior
        val repository = TransactionsImp(apiService, dataBase.getTransactionDao())
        // ahora la capa use case se conecta con el repositorio por medio del parametro repository
        val usecase = TransactionsUseCase(repository)
        val viewModelFactory = ViewModelFactory3(usecase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[RequestViewModel::class.java]
        val botonAzul = binding.hpButtonAzul
        // Se hace clickeable el boton de carga de la wallet
        botonAzul.setOnClickListener {
            val type = "topup"
            val concept = binding.concepto.text.toString()
            val amount = binding.cantidad.text.toString().toLong()
            // Se llama la funcion para hacer el request, se define un numero de cuenta para poder visualizar
            // el request en la wallet
            viewModel.depositarOtransferir(tokenpass, 2171, user, type, concept, amount)
            // Se valida si el envio fue exitoso
            viewModel.transactionResult.observe(this) {transactionOk ->
                if (transactionOk != false) {
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }

            }
            // una ves hecha la carga de dinero, se llama la funcion para volver a la pagina anterior
        }
        //Se hace un Back desde la flecha superior izquierda para volver a la pagina anterior
        val flechaback = binding.imageView
        flechaback.setOnClickListener {
            finish()
        }
    }
}
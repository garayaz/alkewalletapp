package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.databinding.ActivitySendMoneyBinding
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.local.database.WalletDB
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import araya.gonzalo.alkewallwt.viewmodel.SendViewModel
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory2

class SendMoneyActivity : AppCompatActivity() {
    lateinit var binding: ActivitySendMoneyBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val tokenpass = "Bearer ${AlkeWalletApp.token}"
        val account = AlkeWalletApp.createdAwAccount
        val user = AlkeWalletApp.loggedUser!!.id
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val botonVerde = binding.hpButtonVerde
        val apiService = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
        val dataBase = WalletDB.getDatabase(application)
        // a traves del parametro apiService conecto el repositorio con su caa anterior
        val repository = TransactionsImp(apiService, dataBase.getTransactionDao())
        // ahora la capa usecase se conecta con el repositorio po medio del parametro repository
        val useCase = TransactionsUseCase(repository)
        val viewModelFactory = ViewModelFactory2(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[SendViewModel::class.java]
        // Se hace clickeable el boton de envio de dinero por pago
        botonVerde.setOnClickListener {
            val type = "payment"
            val concept = binding.concepto.text.toString()
            val amount = binding.cantidad.text.toString().toLong()
            // Se llama la funcion para hacer el envio de dinero por pago, se define un numero de
            // cuenta para poder visualizar el envío en la wallet y se valida si el saldo es suficiente
            if (amount < AlkeWalletApp.awBalance!!) {
                viewModel.depositarOtransferir(tokenpass, 2172, user, type, concept, amount)
                // Se valida si el envio fue exitoso
                viewModel.transactionResult.observe(this) {transactionOk ->
                    if (transactionOk != false) {
                        val intent = Intent(this, HomePageActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show()
            }
        }
        //Se hace un Back desde la flecha superior izquierda para volver a la pagina anterior
        val flechaback = binding.imageView
        flechaback.setOnClickListener {
            finish()
        }
    }
}
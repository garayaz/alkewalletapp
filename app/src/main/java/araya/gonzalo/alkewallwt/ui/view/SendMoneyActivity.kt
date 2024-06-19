package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.databinding.ActivitySendMoneyBinding
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import araya.gonzalo.alkewallwt.viewmodel.SendViewModel
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory2

class SendMoneyActivity : AppCompatActivity() {
    lateinit var binding: ActivitySendMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val tokenpass = "Bearer ${AlkeWalletApp.token}"
        val account = AlkeWalletApp.createdAwAccount
        val user = AlkeWalletApp.loggedUser!!.id
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("TOKEN en Boton Verde", tokenpass)
        val botonVerde = binding.hpButtonVerde
        val apiService = RetrofitClass.retrofitobj.create(TransactionsService::class.java)
        // a traves del parametro apiService conecto el repositorio con su caa anterior
        val repository = TransactionsImp(apiService)
        // ahora la capa usecase se conecta con el repositorio po medio del parametro repository
        val usecase = TransactionsUseCase(repository)
        val viewModelFactory = ViewModelFactory2(usecase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[SendViewModel::class.java]
        botonVerde.setOnClickListener {
            val type = "payment"
            val concept = binding.concepto.text.toString()
            val amount = binding.cantidad.text.toString().toLong()

            Log.i("TOKEN en Click BVerde", tokenpass)
            viewModel.depositarOtransferir(tokenpass, 2172, user, type, concept, amount)

            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }
        //Se hace un Back desde la flecha superior izquierda para volver a la pagina anterior
        val flechaback = binding.imageView
        flechaback.setOnClickListener {
            finish()
        }
    }
}
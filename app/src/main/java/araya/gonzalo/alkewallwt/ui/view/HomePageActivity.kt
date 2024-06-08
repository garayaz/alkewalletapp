package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import araya.gonzalo.alkewallwt.databinding.ActivityHomePageBinding
import araya.gonzalo.alkewallwt.domain.TransactionsUseCase
import araya.gonzalo.alkewallwt.model.DataObject
import araya.gonzalo.alkewallwt.model.TransactionAW
import araya.gonzalo.alkewallwt.model.TransactionsResponse
import araya.gonzalo.alkewallwt.model.network.RetrofitClass
import araya.gonzalo.alkewallwt.model.network.RetrofitClass.Companion.retrofitobj
import araya.gonzalo.alkewallwt.model.network.TransactionsService
import araya.gonzalo.alkewallwt.model.repository.TransactionsImp
import araya.gonzalo.alkewallwt.ui.adapter.TransactionsViewAdapter
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
//import araya.gonzalo.alkewallwt.ui.adapter.TransactionsViewAdapter
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.loggedUser
import araya.gonzalo.alkewallwt.viewmodel.HomeViewModel
import araya.gonzalo.alkewallwt.viewmodel.ViewModelFactory
import com.google.gson.Gson
import retrofit2.Response
import kotlin.math.absoluteValue

class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding
    val tokenpass = "Bearer ${AlkeWalletApp.token}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("AW LOGGED USER", loggedUser.toString())
        // lleno los datos del encabezado del Home
        binding.hpHola.text = "Hola, ${loggedUser?.first_name}!"

        Log.i("THP - val apiService = retrofitobj.create", binding.toString())
        val apiService = retrofitobj.create(TransactionsService::class.java)
        // a traves del parametro apiService conecto el repositorio con su caa anterior
        val repository = TransactionsImp(apiService)
        // ahora la capa usecase se conecta con el repositorio po medio del parametro repository
        val usecase = TransactionsUseCase(repository)
        // view model no deja instanciar como los anteriores
        var adapterLg = TransactionsViewAdapter()
        Log.i("HPA: ", adapterLg.toString())
        binding.transactions.adapter = adapterLg
        val viewModelFactory = ViewModelFactory(usecase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.transactionsLV.observe(this) {

            Log.i("HPA: ", it.toString())
            binding.transactions.layoutManager = LinearLayoutManager(this)
            Log.i("HPA: ", "despues de bindin.transactions")
            //      val gson = Gson()
            //    Log.i("HPA: gson", gson.toString())
            //   val response = gson.fromJson(it.data, DataObject::class.java)
            Log.i("HPA: response", it.data.toString())
            var totalAmount = 0.0

            for (transaction in it.data) {
                totalAmount += transaction.amount!!
            }
            binding.hpTotal.text = totalAmount.toString()
                    //     Log.i("HPA: dataArray", dataArray.toString())
            adapterLg.transactions = it.data //
            adapterLg.onItemClickistener = { transaction ->
                // se usa it para lenguaje porque asi lo indica el onClickListener, ver en gris
                // despues del parentesis
                //    enviarCorreo(it)
                if (adapterLg.transactions.isEmpty()) {
                    Toast.makeText(this, "No hay transacciones", Toast.LENGTH_SHORT).show()
                    //binding.empty.visibility = View.VISIBLE
                } else {
                    //binding.empty.visibility = View.GONE
                }
            }
        }
        //   val homeViewModel: HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
//        var adapterLg = TransactionsViewAdapter()
//        binding.transactions.adapter = adapterLg
//        viewModel.transactionsLV.observe(this, Observer
//        {
//            binding.transactions.layoutManager = LinearLayoutManager(this)
//            adapterLg.transactions = it // it es MutableList<Languaje>, se ve en la linea 27
//
//
//            // el adapter tiene la expresion lambda onItemClickListener, al que se le puede enviar un bloque de codigo
//            // por tener la expresion lambda por eso se puede poner {}. aca se detecta el click y se ejecuta el codigo asociado.
//            adapterLg.onItemClickistener = { transaction ->
//                // se usa it para lenguaje porque asi lo indica el onClickListener, ver en gris
//                // despues del parentesis
//                //    enviarCorreo(it)
//                if (adapterLg.transactions.isEmpty()) {
//                    Toast.makeText(this, "No hay transacciones", Toast.LENGTH_SHORT).show()
//                    //binding.empty.visibility = View.VISIBLE
//                } else {
//                    //binding.empty.visibility = View.GONE
//                }
//            }
//        })
        //Se define OnClick para que al dar click sobre el boton Verde, salte a la activity Send Money
        // val botonverde = findViewById<Button>(R.id.hp_button_verde)
        val botonverde = binding.hpButtonVerde
        botonverde.setOnClickListener()
        {
            val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java)
            startActivity(abrirPantallaSM)
        }

        //Se define OnClick para que al dar click sobre el boton Azul, salte a la activity Request Money
        // val botonazul = findViewById<Button>(R.id.hp_button_azul)
        val botonazul = binding.hpButtonAzul
        botonazul.setOnClickListener()
        {
            val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java)
            startActivity(abrirPantallaRM)
        }

        //Se define OnClick para que al dar click sobre la foto del usuario, salte a la su perfil
        //val foto = findViewById<ImageView>(R.id.img_hpe_foto)
        val foto = binding.imgHpeFoto
        foto.setOnClickListener()
        {
            val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
            startActivity(abrirPerfil)
        }
    }

    private fun calcBalance(data: MutableList<TransactionAW>, totint: Long): Long {
    Log.i("HPA: calcBalance", totint.toString())
        data.forEach {
            Log.i("HPA: calcBalance", it.amount.toString())
            // sumar amount de cada transaccion
            if (it.type == "topup") {
                totint + it.amount!!
            } else {
                totint - it.amount!!
            }
        }
        return totint
    }
}
//    initAdapter()
//*  val homeViewModel: HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
// se instancia al adaptador, por eso es adaptador()
//    var adapterLg = TransactionsViewAdapter()
// ahora le paso el adapter al recyclerView
//   binding.transactions.adapter = adapterLg

// se instancia el homeviewmodel y se define el observador y que es lo que observara
//    homeViewModel.transactionsLV.observe(this, Observer
//    {
//        binding.transactions.layoutManager = LinearLayoutManager(this)
//        adapterLg.transactions = it // it es MutableList<Languaje>, se ve en la linea 27
//
//
//        // el adapter tiene la expresion lambda onItemClickListener, al que se le puede enviar un bloque de codigo
//        // por tener la expresion lambda por eso se puede poner {}. aca se detecta el click y se ejecuta el codigo asociado.
//        adapterLg.onItemClickistener = { transaction ->
//            // se usa it para lenguaje porque asi lo indica el onClickListener, ver en gris
//            // despues del parentesis
//            //    enviarCorreo(it)
//            if (adapterLg.transactions.isEmpty()) {
//                Toast.makeText(this, "No hay transacciones", Toast.LENGTH_SHORT).show()
//                //binding.empty.visibility = View.VISIBLE
//            } else {
//                //binding.empty.visibility = View.GONE
//            }
//        }

// initAdapter()


//Se define OnClick para que al dar click sobre el boton Verde, salte a la activity Send Money
// val botonverde = findViewById<Button>(R.id.hp_button_verde)
//    val botonverde = binding.hpButtonVerde
//    botonverde.setOnClickListener
//    {
//        val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java)
//        startActivity(abrirPantallaSM)
//    }
//
//    //Se define OnClick para que al dar click sobre el boton Azul, salte a la activity Request Money
//    // val botonazul = findViewById<Button>(R.id.hp_button_azul)
//    val botonazul = binding.hpButtonAzul
//    botonazul.setOnClickListener
//    {
//        val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java)
//        startActivity(abrirPantallaRM)
//    }
//
//    //Se define OnClick para que al dar click sobre la foto del usuario, salte a la su perfil
//    //val foto = findViewById<ImageView>(R.id.img_hpe_foto)
//    val foto = binding.imgHpeFoto
//    foto.setOnClickListener
//    {
//        val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
//        startActivity(abrirPerfil)
//    }
//}
// me creo una variabla para traer o conectar el viewModel se usa el ViewModelProvider que son los proveedores
// o subscriptores que indican quienes estaran en este hilo y se le pasa a quien va a tener el observador a cargo,
// te estoy pasando el proveedor MainViewModel
// un viewmodel va a tener proveedores y en este caso es ViewModelProvider va a escuchar al livedata el mesero,
// los cambios. va de la mano con el viewmodel.  Este HomeViewModel::class.java siempre va a ser un constructor vacio


// aca puedo hacer la configuracion del recyclerview, cuando haya cambio de datos
// ahora comenzamos a configurar el recyclerView, rcv es el id del recyclerView

// Ahora le paso los datos al adaptador por medio del viewModel


// el languagesLV es el que tiene el observer que indica si hay cambios en la lista, le digo quien es
// el que estara observando, le digo "this" esta vista (MainActivity) usando lambda llamo a un
// observador dentro del otro, el de afuera es para comunicarse y el segundo para ejecutar alguna logica
// mediante el obsrvador veo si hay cambios en la data */



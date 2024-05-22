package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import araya.gonzalo.alkewallwt.databinding.ActivityHomePageBinding
import araya.gonzalo.alkewallwt.ui.adapter.TransactionsViewAdapter
import araya.gonzalo.alkewallwt.viewmodel.HomeViewModel

class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeViewModel: HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        // se instancia al adaptador, por eso es adaptador()
        var adapterLg = TransactionsViewAdapter()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        // se instancia el homeviewmodel
        // ahora le paso el adapter al recyclerView
        binding.transactions.adapter = adapterLg
        homeViewModel.transactionsLV.observe(this, Observer {
            binding.transactions.layoutManager = LinearLayoutManager(this)
            adapterLg.transactions = it // it es MutableList<Languaje>, se ve en la linea 27


            // el adapter tiene la expresion lambda onItemClickListener, al que se le puede enviar un bloque de codigo
            // por tener la expresion lambda por eso se puede poner {}. aca se detecta el click y se ejecuta el codigo asociado.
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

            // initAdapter()

        })
        //Se define OnClick para que al dar click sobre el boton Verde, salte a la activity Send Money
        // val botonverde = findViewById<Button>(R.id.hp_button_verde)
        val botonverde = binding.hpButtonVerde
        botonverde.setOnClickListener {
            val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java)
            startActivity(abrirPantallaSM)
        }

        //Se define OnClick para que al dar click sobre el boton Azul, salte a la activity Request Money
        // val botonazul = findViewById<Button>(R.id.hp_button_azul)
        val botonazul = binding.hpButtonAzul
        botonazul.setOnClickListener {
            val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java)
            startActivity(abrirPantallaRM)
        }

        //Se define OnClick para que al dar click sobre la foto del usuario, salte a la su perfil
        // val foto = findViewById<ImageView>(R.id.img_hpe_foto)
        // val foto = binding.imgHpeFoto
        // foto.setOnClickListener {
        //     val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
        //     startActivity(abrirPerfil)
        // }
        // me creo una variabla para traer o conectar el viewModel se usa el ViewModelProvider que son los proveedores
        // o subscriptores que indican quienes estaran en este hilo y se le pasa a quien va a tener el observador a cargo,
        // te estoy pasando el proveedor MainViewModel
        // un viewmodel va a tener proveedores y en este caso es ViewModelProvider va a escuchar al livedata el mesero,
        // los cambios. va de la mano con el viewmodel.  Este HomeViewModel::class.java siempre va a ser un constructor vacio


        // aca puedo hacer la configuracion del recyclerview, cuando haya cambio de datos
        // ahora comenzamos a configurar el recyclerView, rcv es el id del recyclerView

        // Ahora le paso los datos al adaptador por medio del viewModel


        // el languagesLV es el que tiene el observer que indica si hay cambios en la lista, le digo quien es
        // el que estara observando, le diho "this" esta vista (MainActivity) usando lambda llamo a un
        // observador dentro del otro, el de afuera es para comunicarse y el segundo para ejecutar alguna logica
        // mediante el obsrvador veo si hay cambios en la data


    }

}

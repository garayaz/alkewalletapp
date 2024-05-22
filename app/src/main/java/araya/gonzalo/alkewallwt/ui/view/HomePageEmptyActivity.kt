package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.ActivityHomePageEmptyBinding

class HomePageEmptyActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomePageEmptyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  setContentView(R.layout.activity_home_page_empty)

        //Se define OnClick para que al dar click sobre el boton Login, salte a la activity Home Page
        val botonverde = binding.hpButtonVerde
        botonverde.setOnClickListener {
            val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaSM)
        }

        //Se define OnClick para que al dar click sobre el boton Login, salte a la activity Home Page
        val botonazul = binding.hpButtonAzul
        botonazul.setOnClickListener {
            val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaRM)
        }

        //Se define OnClick para que al dar click sobre la foto del usuario, salte a la su perfil
        val foto = binding.imgHpFoto
        foto.setOnClickListener {
            val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
            startActivity(abrirPerfil)
        }
    }
}
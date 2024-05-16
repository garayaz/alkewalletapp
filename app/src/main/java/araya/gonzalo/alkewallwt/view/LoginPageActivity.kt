package araya.gonzalo.alkewallwt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    lateinit var binding : ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se define OnClick para que al dar click sobre el texto "Crear una nueva cuenta", salte a la activity Signup Page
        //val signup = findViewById<TextView>(R.id.lp_crear_cuenta)
        val signup = binding.lpCrearCuenta
        signup.setOnClickListener {
            val abrirPantallaLogin = Intent(this, SignupPage::class.java)
            startActivity(abrirPantallaLogin)
        }

        //Se define OnClick para que al dar click sobre el boton Login, salte a la activity Home Page
        // val boton = findViewById<Button>(R.id.lp_button)
        val boton = binding.lpButton
        boton.setOnClickListener {
            val abrirPantallaHP = Intent(baseContext, HomePageActivity::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaHP)
        }

    }
}
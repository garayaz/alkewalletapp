package araya.gonzalo.alkewallwt.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.databinding.ActivityLoginPageBinding
import araya.gonzalo.alkewallwt.viewmodel.LoginViewModel


class LoginPage : AppCompatActivity() {
    lateinit var binding: ActivityLoginPageBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//  vamos a configurar viewmodel
        // viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        //Se define OnClick para que al dar click sobre el texto "Crear una nueva cuenta", salte a la activity Signup Page
        //val signup = findViewById<TextView>(R.id.lp_crear_cuenta)

        //Vamos a configurar el ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //si el dato es distinto de null lo voy a prerrellenar

        //Configurando en onClick
        binding.lpButton.setOnClickListener {
            //Vamos a rescartar la informacion que ingreso el usuario
            var correoIngresado = binding.lpEnterEmail.text.toString()
            var passwordIngresado = binding.lpEnterContrasena.text.toString()
            //vamos a guardar el correo en los sharedPreferences

            viewModel.hacerLogin(correoIngresado, passwordIngresado)
        }

        //Se configura el observador que va a estar observando al sujeto "loginResultLiveData"
        viewModel.loginResultLiveData.observe(this) { loginOk ->
            if (loginOk == true) {
                val irMenuPrincipal = Intent(this, HomePageActivity::class.java)
                startActivity(irMenuPrincipal)
            } else {
                Toast.makeText(this, "Datos Invalidos", Toast.LENGTH_LONG).show()
            }
        }
        val signup = binding.lpCrearCuenta
        signup.setOnClickListener {

            val abrirPantallaLogin = Intent(this, SignupPage::class.java)
            startActivity(abrirPantallaLogin)
        }
    }
}

/**
//--------------------------------------------------------------------------------------------------------------------
val signup = binding.lpCrearCuenta
signup.setOnClickListener {

val abrirPantallaLogin = Intent(this, SignupPage::class.java)
startActivity(abrirPantallaLogin)
}

//Se define OnClick para que al dar click sobre el boton Login, salte a la activity Home Page
//val boton = findViewById<Button>(R.id.lp_button)
val boton = binding.lpButton
boton.setOnClickListener {
val abrirPantallaHP = Intent(
baseContext,
HomePageActivity::class.java
) // ir a pantalle de creacion de cuenta
startActivity(abrirPantallaHP)
}

}
} **/
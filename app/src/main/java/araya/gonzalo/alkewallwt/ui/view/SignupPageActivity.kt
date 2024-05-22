package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.databinding.ActivitySignupPageBinding
import araya.gonzalo.alkewallwt.model.User
import araya.gonzalo.alkewallwt.viewmodel.RegisterViewModel

class SignupPage : AppCompatActivity() {

    /** codigo nuevo **/
    lateinit var binding: ActivitySignupPageBinding
    private lateinit var viewModel: RegisterViewModel

    //Objeto Usuario que voy a insertar
    lateinit var newUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.lspButton.setOnClickListener {

            val firstName = binding.spInputNombre.text.toString().trim()
            val lastName = binding.spInputApellido.text.toString().trim()
            val email = binding.spEmail.text.toString().trim()
            val password = binding.spInputPassword.text.toString().trim()
            /*val password1 = binding.spInputRepassword.text.toString().trim()*/

            viewModel.hacerRegistro(firstName, lastName, email, password)

            viewModel.RegistrationValid.observe(this) { valid ->
                if (valid) {
                    val intent = Intent(this, HomePageEmptyActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Favor ingresar todos los datos", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.spYaTienesCuenta.setOnClickListener {
            val abrirPantallaLP = Intent(this, LoginPage::class.java)
            startActivity(abrirPantallaLP)
        }
    }
}

/** codigo base, inicial solo salta a las siguientes actividades
lateinit var binding:ActivitySignupPageBinding
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
binding = ActivitySignupPageBinding.inflate(layoutInflater)
setContentView(binding.root)
//        setContentView(R.layout.activity_signup_page)

//Se define OnClick para que al dar click sobre el boton, salte a la activity Home Page Empty
val boton = findViewById<Button>(R.id.lsp_button)
boton.setOnClickListener {
val abrirPantallaHPEmpty = Intent(baseContext, HomePageEmptyActivity::class.java)
startActivity(abrirPantallaHPEmpty)
}

//Se define OnClick para que al dar click sobre el texto "Ya tienes una cuenta?", salte a la activity Login Page
val signup = findViewById<TextView>(R.id.sp_ya_tienes_cuenta)
signup.setOnClickListener {
val abrirPantallaLP = Intent(this, LoginPage::class.java)
startActivity(abrirPantallaLP)
}
} **/
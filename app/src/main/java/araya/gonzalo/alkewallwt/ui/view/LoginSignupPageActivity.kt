package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.ActivityLoginSignupPageBinding
// Esta actividad le da la logica de funcionamiento a los links que contiene, ya sea el
// botón "Crear cuenta nueva" como tambien el link de texto "Ya tienes cuenta?", lo que define
// los 2 caminos posibles para el usuario.
//
class LoginSignupPageActivity : AppCompatActivity() {
    // Se declara la variable dinding que nos conectará y nos dara acceso a los datos del
    // XML respectivo "Login_signup_page_activity"
    lateinit var binding : ActivityLoginSignupPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // a traves de la funcion inflate se declara el binding para acceder a los
        // elementos de la vista y se le pasa el layoutInflater para que pueda acceder
        // al layout del archivp "Login_signup_page_activity", con esto view binding
        // tiene acceso a los elementos del xml de la actividad.
        binding = ActivityLoginSignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Se declara la variable boton que nos conectará y nos dara acceso al boton definido
        // con el id "lspButton" en el xml de la actividad.
        val boton = binding.lspButton
        // se define el evento click para el boton "Crear cuenta nueva" y se indica que al dar
        // click a este, se va a abrir la actividad "SignupPage"
        boton.setOnClickListener {
            val abrirPantallaLSP = Intent(baseContext, SignupPage::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaLSP)
        }
        // Se define el evento click para el link en el texto "Ya tienes cuenta?" y se indica que
        // al dar click a este, se va a abrir la actividad "Login Page".
        binding.yaTienesCuenta.setOnClickListener(){
            val abrirPantallaLP = Intent(baseContext, LoginPage::class.java)
            startActivity(abrirPantallaLP)
        }

    }
}
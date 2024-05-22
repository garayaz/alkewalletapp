package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.ActivityLoginSignupPageBinding
// Esta actividad le da la logica de funcionamiento a los links que contiene, ya sea el
// botón "Crear cuenta nueva" como tambien el link de texto "Ya tienes cuenta?",
//
class LoginSignupPageActivity : AppCompatActivity() {
    // Se declara la variable dinding que nos conectara y nos dara acceso a los datos del
    // XML respectivo "Login_signup_page_activity"
    lateinit var binding : ActivityLoginSignupPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val boton = binding.lspButton
        boton.setOnClickListener {
            val abrirPantallaLSP = Intent(baseContext, SignupPage::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaLSP)
        }
        binding.yaTienesCuenta.setOnClickListener(){
            val abrirPantallaLP = Intent(baseContext, LoginPage::class.java)
            startActivity(abrirPantallaLP)
        }

    }
}
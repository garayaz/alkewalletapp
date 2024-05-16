package araya.gonzalo.alkewallwt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.ActivityLoginSignupPageBinding

class LoginSignupPageActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginSignupPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_login_signup_page)

        /* Abiendo la pantalla con un click */

        //Se declara la imagen como una variable
     //   val boton = findViewById<Button>(R.id.lsp_button)
        val boton = binding.lspButton
        boton.setOnClickListener {
            val abrirPantallaLSP = Intent(baseContext, SignupPage::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaLSP)
        }

       // val tv = findViewById<TextView>(R.id.ya_tienes_cuenta)
        val tv = binding.yaTienesCuenta
        tv.setOnClickListener {
            val abrirPantallaLP = Intent(baseContext, LoginPage::class.java)
            startActivity(abrirPantallaLP)
        }

    }
}
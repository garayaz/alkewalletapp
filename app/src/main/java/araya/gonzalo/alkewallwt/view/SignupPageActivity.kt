package araya.gonzalo.alkewallwt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.databinding.ActivitySignupPageBinding

class SignupPage : AppCompatActivity() {
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
    }
}
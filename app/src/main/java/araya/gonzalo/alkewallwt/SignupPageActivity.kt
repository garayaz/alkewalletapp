package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SignupPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

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
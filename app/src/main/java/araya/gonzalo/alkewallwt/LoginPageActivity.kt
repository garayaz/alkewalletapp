package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        //Se define OnClick para que al dar click sobre el texto "Crear una nueva cuenta", salte a la activity Signup Page
        val signup = findViewById<TextView>(R.id.lp_crear_cuenta)
        signup.setOnClickListener {
            val abrirPantallaLogin = Intent(this, SignupPage::class.java)
            startActivity(abrirPantallaLogin)
        }

        //Se define OnClick para que al dar click sobre el boton Login, salte a la activity Home Page
        val boton = findViewById<Button>(R.id.lp_button)
        boton.setOnClickListener {
            val abrirPantallaHP = Intent(baseContext, HomePageActivity::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaHP)
        }

    }
}
package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginSignupPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup_page)

        /* Abiendo la pantalla con un click */

        //Se declara la imagen como una variable
        val boton = findViewById<Button>(R.id.lsp_button)
        boton.setOnClickListener {
            val abrirPantallaLSP = Intent(baseContext, SignupPage::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaLSP)
        }

        val tv = findViewById<TextView>(R.id.ya_tienes_cuenta)
        tv.setOnClickListener {
            val abrirPantallaLP = Intent(baseContext, LoginPage::class.java)
            startActivity(abrirPantallaLP)
        }

    }
}
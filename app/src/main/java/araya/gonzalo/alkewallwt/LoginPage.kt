package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val signup = findViewById<TextView>(R.id.lp_crear_cuenta_nueva)
        signup.setOnClickListener {
            val abrirPantallaLogin = Intent(this, SignupPage::class.java)
            abrirPantallaLogin.putExtra("nombre", "Gonzalo")
            abrirPantallaLogin.putExtra("apellido", "Araya")
            abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaLogin)
        }

    }
}
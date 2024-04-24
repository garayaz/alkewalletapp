package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginSignupPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signup = findViewById<Button>(R.id.button)
        signup.setOnClickListener {
            val abrirPantallaLogin = Intent(this, SignupPage::class.java)
            abrirPantallaLogin.putExtra("nombre", "Gonzalo")
            abrirPantallaLogin.putExtra("apellido", "Araya")
            abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaLogin)
        }

    }
}
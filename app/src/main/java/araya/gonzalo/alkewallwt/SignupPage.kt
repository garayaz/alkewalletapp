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

        //Se declara la imagen como una variable
        val boton = findViewById<TextView>(R.id.sp_ya_tienes_cuenta)
        boton.setOnClickListener {
            val abrirPantallaLP = Intent(baseContext, LoginPage::class.java) // ir a pantalle de creacion de cuenta
            startActivity(abrirPantallaLP)
        }
    }
}
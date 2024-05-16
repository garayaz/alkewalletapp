package araya.gonzalo.alkewallwt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.SplashscreenactivityBinding
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: SplashscreenactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.splashscreenactivity)
        binding = SplashscreenactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        /**
        /* Abiendo la pantalla con un click */

        //Se declara la imagen como una variable
        val logoApp = findViewById<ImageView>(R.id.logoApp)
        logoApp.setOnClickListener {
            val abrirPantallaLogin = Intent(baseContext, LoginSignupPageActivity::class.java)
            abrirPantallaLogin.putExtra("nombre", "Gonzalo")
            abrirPantallaLogin.putExtra("apellido", "Araya")
            abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaLogin)
        }


         * Abiendo la pantalla con un timer*/

        var task: TimerTask? = object : TimerTask() {
            override fun run() {
                val abrirPantallaLogin = Intent(baseContext, LoginSignupPageActivity::class.java)
              //  abrirPantallaLogin.putExtra("nombre", "Gonzalo")
              //  abrirPantallaLogin.putExtra("apellido", "Araya")
              //  abrirPantallaLogin.putExtra("acepto_tyC", false)
                startActivity(abrirPantallaLogin)
              //  finish()
            }
        }

        val timer = Timer()
        timer.schedule(task, 3000)
    }
}
package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreenactivity)



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

        /**
         * Abiendo la pantalla con un timer*/
/*
        var task: TimerTask? = object : TimerTask() {
            override fun run() {
                val abrirPantallaLogin = Intent(baseContext, LoginSignupPageActivity::class.java)
                abrirPantallaLogin.putExtra("nombre", "Gonzalo")
                abrirPantallaLogin.putExtra("apellido", "Araya")
                abrirPantallaLogin.putExtra("acepto_tyC", false)
                startActivity(abrirPantallaLogin)
                finish()
            }
        }

        val timer = Timer()
        timer.schedule(task, 3000)*/
    }
}
package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.SplashscreenactivityBinding
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {
    // se declara la variable binding, necesaria para el viewBinding, que se utiliza
    // para acceder a los elementos de la vista
    lateinit var binding: SplashscreenactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // a traves de la funcion inflate se declara el binding para acceder a los
        // elementos de la vista y se le pasa el layoutInflater para que pueda acceder
        // al layout del archivp xml del splashscreenactivity, con esto view binding
        // tiene acceso a los elementos de la vista splashscreenactivity.xml
        binding = SplashscreenactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var task: TimerTask? = object : TimerTask() {
            override fun run() {
                // Intent indica que se tratara de abrir l vista LoginSignupPageActivity
                val abrirPantallaLogin = Intent(baseContext, LoginSignupPageActivity::class.java)
                // se inicia la actividad, es decir se abre la vista en el dispositivo.
                startActivity(abrirPantallaLogin)
              //  finish()
            }
        }

        val timer = Timer()
        timer.schedule(task, 3000)
    }
}
package araya.gonzalo.alkewallwt.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import araya.gonzalo.alkewallwt.databinding.ActivityRequestMoneyBinding

class RequestMoneyActivity : AppCompatActivity() {
    lateinit var binding : ActivityRequestMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se hace un Back desde la flecha superior izquierda para volver a la pagina anterior
        val flechaback = binding.imageView
        flechaback.setOnClickListener {
            finish()
        }
    }
}
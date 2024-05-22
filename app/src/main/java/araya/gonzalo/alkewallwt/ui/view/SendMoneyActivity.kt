package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import araya.gonzalo.alkewallwt.databinding.ActivitySendMoneyBinding

class SendMoneyActivity : AppCompatActivity() {
    lateinit var binding:ActivitySendMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se hace un Back desde la flecha superior izquierda para volver a la pagina anterior
        val flechaback = binding.imageView
        flechaback.setOnClickListener {
            finish()
        }
    }
}
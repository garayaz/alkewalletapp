package araya.gonzalo.alkewallwt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class RequestMoneyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_money)

        //Se hace un Back desde la flecha superior izquierda para volver a la pagina anterior
        val flechaback = findViewById<ImageView>(R.id.imageView)
        flechaback.setOnClickListener {
            finish()
        }
    }
}
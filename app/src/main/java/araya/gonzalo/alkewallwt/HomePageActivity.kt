package araya.gonzalo.alkewallwt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //Se define OnClick para que al dar click sobre el boton Verde, salte a la activity Send Money
        val botonverde = findViewById<Button>(R.id.hp_button_verde)
        botonverde.setOnClickListener {
            val abrirPantallaSM = Intent(baseContext, SendMoneyActivity::class.java)
            startActivity(abrirPantallaSM)
        }

        //Se define OnClick para que al dar click sobre el boton Azul, salte a la activity Request Money
        val botonazul = findViewById<Button>(R.id.hp_button_azul)
        botonazul.setOnClickListener {
            val abrirPantallaRM = Intent(baseContext, RequestMoneyActivity::class.java)
            startActivity(abrirPantallaRM)
        }

        //Se define OnClick para que al dar click sobre la foto del usuario, salte a la su perfil
        val foto = findViewById<ImageView>(R.id.img_hpe_foto)
        foto.setOnClickListener {
            val abrirPerfil = Intent(baseContext, ProfilePageActivity::class.java)
            startActivity(abrirPerfil)
        }
    }

}
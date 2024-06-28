package araya.gonzalo.alkewallwt.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.databinding.ActivityLoginPageBinding
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.loggedUser
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.token
import araya.gonzalo.alkewallwt.viewmodel.LoginViewModel


class LoginPage : AppCompatActivity() {
    lateinit var binding: ActivityLoginPageBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Vamos a configurar el ViewModel que conecta a la vista
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Configurando en onClick para el boton Login
        binding.lpButton.setOnClickListener {
            //Vamos a rescartar la informacion que ingreso el usuario
            var correoIngresado = binding.lpEnterEmail.text.toString()
            var passwordIngresado = binding.lpEnterContrasena.text.toString()
// llamada al metodo doLogin definido en el ViewModel "LoginViewModel"
            viewModel.doLogin(correoIngresado, passwordIngresado)
        }

        //Se configura el observador que va a estar observando al sujeto "tokenResultLiveData", este
        // puede traer el accessToken o nulo si es que no se logeo correctamente
        viewModel.tokenResultLiveData.observe(this) { tokenOk ->
            if (tokenOk != null) {
                //Se guardan el token en la variable global "token"
                token = tokenOk
                // se llama la api que trae los datos del usuario, si el usuario viene desde el signup
                // en esta funcion se llama a la funcion que crea la cuenta wallet del usuario
                viewModel.getUserData()
            } else {
                Toast.makeText(this, "Error al logearse", Toast.LENGTH_SHORT).show()
            }
        }
        //  el observador "usuarioLiveData", viene  del ViewModel "LoginViewModel" con valor null
        // si no se logeo correctamente, de lo contrario trae los datos del usuario (objeto)
        viewModel.usuarioLiveData.observe(this) { usuario ->
            if (usuario != null) {   // si se logeo correctamente llama a la HomePageActivity
                loggedUser = usuario
                val irMenuPrincipal = Intent(this, HomePageActivity::class.java)
                startActivity(irMenuPrincipal)
            } else {
                Toast.makeText(this, "Error al logearse", Toast.LENGTH_SHORT).show()
            }
        }
        val signup = binding.lpCrearCuenta
        signup.setOnClickListener {

            val abrirPantallaLogin = Intent(this, SignupPage::class.java)
            startActivity(abrirPantallaLogin)
        }
    }
}
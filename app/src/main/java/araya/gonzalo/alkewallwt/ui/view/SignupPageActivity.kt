package araya.gonzalo.alkewallwt.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import araya.gonzalo.alkewallwt.R
import araya.gonzalo.alkewallwt.databinding.ActivitySignupPageBinding
import araya.gonzalo.alkewallwt.model.AccountRequest
import araya.gonzalo.alkewallwt.model.User
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp
import araya.gonzalo.alkewallwt.viewmodel.AlkeWalletApp.Companion.fromRegister
import araya.gonzalo.alkewallwt.viewmodel.NewAccountViewModel
import araya.gonzalo.alkewallwt.viewmodel.RegisterViewModel
import java.time.LocalDateTime

class SignupPage : AppCompatActivity() {

    /** desde esta actividad se crea una nueva cuenta **/
    lateinit var binding: ActivitySignupPageBinding

    // se define la variable viewModel, que conecta esta actividad con su ViewModel respectivo, es
    // decir con el "mesero"
    private lateinit var viewModel: RegisterViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
// se conecta esta actividad con su ViewModel respectivo "RegisterViewModel"
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
// se define el evento onclick sobre el boton de registro
        binding.lspButton.setOnClickListener {
// se definen las variables que contienen los datos del usuario y se le pasan los datos que estan
            // en el xml
            val firstName = binding.spInputNombre.text.toString().trim()
            val lastName = binding.spInputApellido.text.toString().trim()
            val email = binding.spEmail.text.toString().trim()
            val password = binding.spInputPassword.text.toString().trim()
            val repassword = binding.spInputRepassword.text.toString().trim()
            /*val password1 = binding.spInputRepassword.text.toString().trim()*/
// se llama la funcion que hace el registro, pasando los datos del usuario.
            viewModel.userRegister(firstName, lastName, email, password, repassword)
// RegistrationValid es un LiveData que se define en el ViewModel y que escucha los cambios, es
// decir cuando cambia de valor a true o false.
            viewModel.RegistrationValid.observe(this) { valid ->

                // si es true, se salta a la actividad LoginPage
                if (valid) {
                    viewModel.fromRegisterResultLiveData.observe(this) { registered ->
                        if (registered) {
                            fromRegister = registered
                        }
                        val intent = Intent(this, LoginPage::class.java)
                        startActivity(intent)
                    }
                } else {
                    // Si es false, se muestra un mensaje de error.
                    Toast.makeText(this, "Favor ingresar todos los datos", Toast.LENGTH_LONG).show()
                }
            }
        }
        // Si el usuario presiona el texto "Ya tienes una cuenta?", se salta a la activity Login Page.
        binding.spYaTienesCuenta.setOnClickListener {
            val abrirPantallaLP = Intent(this, LoginPage::class.java)
            startActivity(abrirPantallaLP)
        }
    }
}
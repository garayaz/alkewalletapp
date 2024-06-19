package araya.gonzalo.alkewallwt.viewmodel

import android.app.Application
import araya.gonzalo.alkewallwt.model.AccountResponse
import araya.gonzalo.alkewallwt.model.User


// Clase Gobal que se configura an Manifest en Application (android:name=".viewmodel.AlkeWalletApp")
class AlkeWalletApp : Application() {

    companion object {
        // se crea un objeto usuario que va a estar global al proyecto
        var loggedUser: User? = null

        // variable que almacena los datos de la cuenta creada
        var awAccount: AccountResponse? = null

        // se crea un string para almacenar el token
        var token: String? = ""

        // ver como guardar el response del login
        var createdAwAccount: Int? = null

        // variable global para indicar si el usuario se esta logeando inmediatamente
    // despues del signup
        var fromRegister : Boolean = false

    }

    override fun onCreate() {
        // Se limpian las variables cada vez que se inicia la app
        super.onCreate()
        loggedUser = null
        token = null
        createdAwAccount = null
        fromRegister = false
    }
}

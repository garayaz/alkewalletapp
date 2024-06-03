package araya.gonzalo.alkewallwt.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {
    // este es unico y se utiliza para todos los llamados a la API, el companion object define el
    // objeto de esta clase para que no se tenga que estar instanciando cada vez que se
    // llama a la API
    companion object {
        // el interceptor es para ver la respuesta de la API en el log, para esto se debe instalar
        // la dependencia okhttp3-logging-interceptor en el gradle
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter)
        }.build()
        // este es el objeto que se utiliza para hacer las peticiones a la API
        val retrofitobj = Retrofit.Builder()
            .client(client)
            .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create()) //mapea el objeto json a un objeto de kotlin
            .build()

    }
}
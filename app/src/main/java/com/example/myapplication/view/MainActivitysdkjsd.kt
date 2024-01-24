import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.network.LocalisationApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivitysdkjsd : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("test debut", "yooo")

        // Initialiser Retrofit pour faire l'appel à l'API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(LocalisationApi::class.java)

        // Faire l'appel à l'API de manière asynchrone avec RxJava
        apiService.getLatLong("alger", "AIzaSyDBlWUao8U9kxDpb3_9OT9BlNeWsKMJ46A")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    Log.d(TAG, "Réponse de l'API: $response")
                },
                { error ->
                    Log.e(TAG, "Erreur lors de la demande à l'API: ${error.message}")
                }
            )
    }
}

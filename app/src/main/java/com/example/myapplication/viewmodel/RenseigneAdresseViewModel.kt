import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.network.observeOnce
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RenseigneAdresseViewModel(private val listeMagasinProcheViewModel: ListeMagasinProcheViewModel, private val context: Context) {

    suspend fun getLocationPosition(fusedLocationProviderClient: FusedLocationProviderClient, coroutineScope: CoroutineScope): Adresse? {
        return suspendCoroutine { continuation ->
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                continuation.resume(null)
            } else {
                val locationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(1000)

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        locationResult.lastLocation?.let { location ->
                            val latitude = location.latitude
                            val longitude = location.longitude

                            coroutineScope.launch {
                                val position = getAdress(Pair(latitude, longitude))
                                continuation.resume(position)
                            }
                            fusedLocationProviderClient.removeLocationUpdates(this)
                        }
                    }
                }
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    null
                )
            }
        }
    }

    suspend fun getAdress(coordinate: Pair<Double, Double>): Adresse {
        return suspendCoroutine { continuation ->
            listeMagasinProcheViewModel.positionData.observeOnce { addressRecup ->
                continuation.resume(addressRecup)
            }
            listeMagasinProcheViewModel.getAdress(coordinate)
        }
    }
}

package com.example.myapplication.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.LocalisationResponseObject
import com.example.myapplication.repositories.LocalisationRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LocalisationViewModel(private val repository: LocalisationRepository) : ViewModel() {

    private val _localisationData = MutableLiveData<LocalisationResponseObject>()
    val localisationData: LiveData<LocalisationResponseObject> get() = _localisationData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getLatLong(address: String) {
        repository.getLatLong(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ localisationResponse ->
                _localisationData.value = localisationResponse
            }, { throwable ->
                _error.value = "Erreur lors de la récupération des données de localisation"
            })
    }
}

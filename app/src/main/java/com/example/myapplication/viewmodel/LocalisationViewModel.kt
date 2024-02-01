package com.example.myapplication.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.LocalisationData
import com.example.myapplication.network.LocalisationMapper
import com.example.myapplication.network.LocalisationResponseObject
import com.example.myapplication.repositories.LocalisationRepository
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class LocalisationViewModel(private val repository: LocalisationRepository,private val mapper: LocalisationMapper)
    : ViewModel() {

    private val _localisationData = MutableLiveData<LocalisationData>()
    val localisationData: MutableLiveData<LocalisationData> get() = _localisationData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getLatLong(address: String) {
        val reponse = mapper.mapping(repository.getLatLong(address).blockingFirst())
        _localisationData.postValue(reponse)

    }
}

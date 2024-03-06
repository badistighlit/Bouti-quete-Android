package com.example.myapplication.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.network.LocalisationData
import com.example.myapplication.network.LocalisationMapper
import com.example.myapplication.network.LocalisationPosition
import com.example.myapplication.repositories.DatabaseRepository
import com.example.myapplication.repositories.LocalisationRepository
import org.koin.java.KoinJavaComponent
import kotlin.coroutines.CoroutineContext

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class ListeMagasinProcheViewModel(private val repository: LocalisationRepository, private val mapper: LocalisationMapper)
    : ViewModel() {

    val databaseRepository: DatabaseRepository by KoinJavaComponent.inject(DatabaseRepository::class.java);
    init {
        databaseRepository.buildIfNeeded()

    }
    val magasins =databaseRepository.getMagasins();

    val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    private val _localisationData = MutableLiveData<LocalisationData>()
    private val _positionData = MutableLiveData<Adresse>()
    val localisationData: MutableLiveData<LocalisationData> get() = _localisationData
    val positionData: MutableLiveData<Adresse> get() = _positionData


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getLatLong(address: String) {
        val response = mapper.mapping(repository.getLatLong(address).blockingFirst())
        _localisationData.postValue(response)
    }

    fun getAdress(coordinate: Pair<Double, Double>) {
        val response = mapper.mappingAdress(repository.getAddress(coordinate).blockingFirst())
        _positionData.postValue(response)
    }

}
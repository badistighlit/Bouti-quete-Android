package com.example.myapplication.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.network.LocalisationData
import com.example.myapplication.network.LocalisationMapper
import com.example.myapplication.network.observeOnce
import com.example.myapplication.repositories.DatabaseRepository
import com.example.myapplication.repositories.LocalisationRepository
import org.koin.java.KoinJavaComponent
import kotlin.coroutines.CoroutineContext
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

 class ListeMagasinProcheViewModel(private val repository: LocalisationRepository, private val mapper: LocalisationMapper)
    : ViewModel() {

    val databaseRepository: DatabaseRepository by KoinJavaComponent.inject(DatabaseRepository::class.java);
    init {
        databaseRepository.build()

    }
    val magasins =databaseRepository.getMagasins();

    val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    private val _localisationData = MutableLiveData<LocalisationData>()
    val localisationData: MutableLiveData<LocalisationData> get() = _localisationData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getLatLong(address: String) {
        val reponse = mapper.mapping(repository.getLatLong(address).blockingFirst())
        _localisationData.postValue(reponse)

    }







}

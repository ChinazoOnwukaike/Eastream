package com.eastream.eastream

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.repository.UserPreferences
import com.eastream.eastream.repository.UserPreferencesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class MainActivityViewModel()
//@Inject constructor(private val UserPreferencesRepository: UserPreferencesRepository)
    : ViewModel() {

//    @Inject lateinit var preferences: UserPreferences

    private val _isDarkMode: MutableState<Boolean> =
        mutableStateOf(value = true)
    var isDarkMode: State<Boolean> = _isDarkMode

//    var isNightMode = preferences.isNightMode


    //Data Loading State
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val db = FirebaseFirestore.getInstance()

//    fun updateThemeMode () {
//        viewModelScope.launch(Dispatchers.IO) {
//            UserPreferencesRepository.updateIsNightMode(!isNightMode)
//        }
//    }
//
//    fun retrieveData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            UserPreferencesRepository.fetchInitialPreferences()
//        }
//    }
}
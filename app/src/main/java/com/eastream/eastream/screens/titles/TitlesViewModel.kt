package com.eastream.eastream.screens.titles

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.model.ETitle
import com.eastream.eastream.screens.search.SearchWidgetState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TitlesViewModel: ViewModel() {
    private val _loading : MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val db = FirebaseFirestore.getInstance()

    private val _listOfTitles: MutableState<List<BasicTitleInfo>> = mutableStateOf(listOf<BasicTitleInfo>())
    val listOfTitles: State<List<BasicTitleInfo>> = _listOfTitles

    val networks = listOf("Netflix", "Rakuten Viki", "Hulu", "Amazon Prime Video", "The Roku Channel", "Tubi TV")

    private val _networkName: MutableState<String> = mutableStateOf(networks[0])
    val networkName : State<String> = _networkName

    fun getTitles(network: String) = viewModelScope.launch  {
            //        delay(2000)
            _loading.value = true
            db.collection("titles")
                .whereArrayContains("networks", network)
                .get()
                .addOnSuccessListener { docs ->
                    if (docs != null) {
                        val titles = docs.toObjects(BasicTitleInfo::class.java)
                        _listOfTitles.value = titles
                        _loading.value = false
                    } else Log.d("FB", "getTitles: Didn't work")
                }
                .addOnFailureListener {
                    Log.d("FB", "getTitles: ", it)
                }
//            _loading.value = false
    }

    fun updateNetworkName(newName: String) = viewModelScope.launch{
        _networkName.value = newName
    }

    fun toggleLoading() = viewModelScope.launch {
        _loading.value = !_loading.value!!
    }
}




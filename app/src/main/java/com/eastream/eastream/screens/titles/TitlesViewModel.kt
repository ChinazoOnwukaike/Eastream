package com.eastream.eastream.screens.titles

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.model.ETitle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//@HiltViewModel
class TitlesViewModel: ViewModel() {
//    private val _loading : MutableState<Boolean> = mutableStateOf(false)
    var loading = mutableStateOf(false)
//    val titles : MutableList<HashMap<String, Any>> = MutableList<Any>()
    private val db = FirebaseFirestore.getInstance()

    fun getTitles(network: String, listOfTitles: MutableState<List<BasicTitleInfo>>) = viewModelScope.launch  {
        loading.value = true
//        delay(2000)
        db.collection("titles")
            .whereArrayContains("networks", network)
            .get()
            .addOnSuccessListener { docs ->
                if (docs != null) {
                    val titles = docs.toObjects(BasicTitleInfo::class.java)
                    listOfTitles.value = titles
//                    loading.value = false
                    } else Log.d("FB", "getTitles: Didn't work")
                }
            .addOnFailureListener{
                Log.d("FB", "getTitles: ", it)
                    }
        loading.value = false
    }
}



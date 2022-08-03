package com.eastream.eastream.screens.titles

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.model.ETitle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


//@HiltViewModel
class TitlesViewModel: ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
//    val titles : MutableList<HashMap<String, Any>> = MutableList<Any>()
    private val db = FirebaseFirestore.getInstance()

    fun getTitles(network: String, listOfTitles: MutableState<List<BasicTitleInfo>>) = viewModelScope.launch  {

            if (_loading.value == false){
                _loading.value = true

                db.collection("titles")
                .whereArrayContains("networks", network)
                .get()
                .addOnSuccessListener { docs ->
                    if (docs != null) {
                    val titles = docs.toObjects(BasicTitleInfo::class.java)
                        listOfTitles.value = titles
                    } else Log.d("FB", "getTitles: Didn't work")
                }
                    .addOnFailureListener{
                        Log.d("FB", "getTitles: ", it)
                    }
                _loading.value = false
            }
    }
}



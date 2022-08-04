package com.eastream.eastream.screens.userprofile

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.BasicTitleInfo
import com.eastream.eastream.model.ETitle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val db = FirebaseFirestore.getInstance()

    fun getUserTitles(network: String, listOfTitles: MutableState<List<BasicTitleInfo>>) = viewModelScope.launch {

        if (_loading.value == false){
            _loading.value = true

            if (userId != null) {
                db.collection("users").document(userId).collection("favorites")
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
            }
            _loading.value = false

    }
}}
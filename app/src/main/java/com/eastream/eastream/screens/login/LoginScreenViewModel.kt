package com.eastream.eastream.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.EUser
import com.eastream.eastream.model.Favorites
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginScreenViewModel : ViewModel(){
//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, userProfile: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful) {
                        Log.d("FB", "signInWithEmailAndPassword: We did it! ${task.result.toString()}")
                        //todo: take to userprofile
                        userProfile()
                    } else {
                        Log.d("FB", "signInWithEmailAndPassword: ${task.result.toString()}")
                    }
                }
        } catch (ex: Exception) {
            Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, userProfile: () -> Unit) {
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        userProfile()
                    } else {
                        Log.d("FB", "createUserWithEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid

        val user = EUser(userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            fName = "",
            lName = "",
            id = null).toMap()

        val favs = Favorites(titles = mutableListOf<String>())
        FirebaseFirestore.getInstance().collection("users").add(user)
                .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    Log.d("FB", "createUser: Created! ${task.result.id}")
                    val docId = task.result.id
                    FirebaseFirestore.getInstance().collection("users").document(docId).collection("favorites").add(favs)
                } else {
                    Log.d("FB", "signInWithEmailAndPassword: ${task.result.toString()}")
                }
            }

    }


}
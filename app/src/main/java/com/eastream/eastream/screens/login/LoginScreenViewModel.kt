package com.eastream.eastream.screens.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.ETitle
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
        val db = FirebaseFirestore.getInstance()
        var docInfo = mapOf<String, Any>()
        val user = EUser(userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            fName = "",
            lName = "",
            id = userId).toMap()

        val favs = Favorites(titles = mutableListOf<String>())
        if (userId != null) {
            FirebaseFirestore.getInstance().collection("users").document(userId).set(user)
                .addOnSuccessListener{doc ->
                   val docRef = db.collection("users").document(userId).get()
                    docRef.addOnSuccessListener {  doc ->
                        if (doc != null){
                        val newDoc = doc.toObject(ETitle::class.java)
                            docInfo = newDoc?.toMap() as Map<String, Any>
                        }
                    }
                    if (doc != null) {
                        Log.d("FB", "createUser: Created! ${docInfo["id"]}")
                        val docId = docInfo["id"]
                        db.collection("users").document(docId as String).collection("favorites").add(favs)
                    } else {
                        Log.d("FB", "signInWithEmailAndPassword: New collection creation error")
                    }
                }
        }

    }


}
package com.eastream.eastream.screens.titleinfo

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.ETitle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class TitleInfoViewModel: ViewModel() {
//    private val _loading = MutableLiveData(false)
    var loading = mutableStateOf(false)

    private val _titleInfo: MutableState<ETitle> = mutableStateOf(ETitle())
    val titleInfo : State<ETitle> = _titleInfo

    private val _isLiked: MutableState<Boolean> = mutableStateOf(false)
    val isLiked: State<Boolean> = _isLiked

    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val db = FirebaseFirestore.getInstance()

    fun getOneTitle(titleId:String?) = viewModelScope.launch {
//        Log.d("FB", "getOneTitle: $titleId")
            loading.value = true


            if (titleId != null) {
                db.collection("titles").document(titleId).get()
                    .addOnSuccessListener { doc ->
                        if (doc != null) {
                            val titleInfoDoc = doc.toObject(ETitle::class.java)

                            if (titleInfoDoc != null) {
                                _titleInfo.value = titleInfoDoc
                                Log.d("FB", "data: ${titleInfo.value} ")
                            }
                        } else Log.d("FB", "getOneTitle: didn't work")
//                    }
//                    .addOnFailureListener{
//                        Log.d("FB", "getOneTitle: ", it){
                    }
        }
        loading.value = false
    }

    fun checkInDb(titleId: String, isLiked: MutableState<Boolean> = mutableStateOf(false)) = viewModelScope.launch {
            loading.value = true

            val docRef = userId?.let { db.collection("users").document(it).collection("favorites").document(titleId) }

            if (userId != null) {
                docRef?.get()?.addOnCompleteListener { task ->
                    val doc = task.result
                    if (doc != null) {
                        if (doc.exists()) {
                            Log.d("FB", "getTitles: exists")
                            _isLiked.value = true}
                        else {
                            _isLiked.value = false
                        }
                    }
                }?.addOnFailureListener{
                    Log.d("FB", "getTitles: not there", it)
                }
            }
            loading.value = false
    }

    fun addTitle(titleId: String, titleData: Map<String, Any>) = viewModelScope.launch {
        loading.value = true

            if (userId != null) {
                db.collection("users").document(userId).collection("favorites").document(titleId)
                    .set(titleData)
                    .addOnSuccessListener {
                        Log.d("FB", "getTitles: Title added")
                    }
                    .addOnFailureListener{
                        Log.d("FB", "getTitles: Title not added", it)
                    }
            }
            loading.value = false
    }

    fun deleteTitle(titleId: String) = viewModelScope.launch {
        loading.value = true

            if (userId != null) {
                db.collection("users").document(userId).collection("favorites").document(titleId)
                    .delete()
                    .addOnSuccessListener {
                        Log.d("FB", "getTitles: Title deleted")
                    }
                    .addOnFailureListener{
                        Log.d("FB", "getTitles: Title not deleted", it)
                    }
            }
            loading.value = false
        }

    fun toggleLike() = viewModelScope.launch {
        _isLiked.value = !_isLiked.value
    }
}
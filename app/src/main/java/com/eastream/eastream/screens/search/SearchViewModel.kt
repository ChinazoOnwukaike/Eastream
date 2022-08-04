package com.eastream.eastream.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eastream.eastream.model.BasicTitleInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    //Search Widget
    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    //Search Text
    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    //Data Loading State
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    //
    private val _listOfTitles: MutableState<List<BasicTitleInfo>> =
        mutableStateOf(value = listOf(BasicTitleInfo()))
    val listOfTitles: State<List<BasicTitleInfo>> = _listOfTitles

    private val db = FirebaseFirestore.getInstance()


    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun getSearchResults(searchText:String) = viewModelScope.launch {
        if (_loading.value == false) {
            _loading.value = true
            db.collection("titles")
                .whereGreaterThanOrEqualTo("title", searchText)
                .whereLessThanOrEqualTo("title", searchText + "\uf8ff")
                .get()
                .addOnSuccessListener { docs ->
                    if (docs != null) {
                        val titles = docs.toObjects(BasicTitleInfo::class.java)
                        _listOfTitles.value = titles
                    } else Log.d("FB", "getTitles: Didn't work")
                }
                .addOnFailureListener{
                    Log.d("FB", "getTitles: ", it)
                }

            _loading.value = false
        }
    }
}
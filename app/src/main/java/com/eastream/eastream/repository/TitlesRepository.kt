package com.eastream.eastream.repository

import com.eastream.eastream.data.DataOrException
import com.eastream.eastream.model.ETitle
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class TitlesRepository @Inject constructor(){
    private val dataOrException = DataOrException<List<ETitle>, Boolean, Exception>()
    private val titleInfoDataOrException = DataOrException<ETitle, Boolean, Exception>()
    private val db = FirebaseFirestore.getInstance()

    suspend fun getTitles(): DataOrException<List<ETitle>, Boolean, Exception> {
        try {
            dataOrException.loading = true
//            dataOrException.data = db.collection("titles")
//                .whereArrayContains("networks", network)
//                .get()
            if (dataOrException.data!!.isNotEmpty()) dataOrException.loading = false
        } catch (e: Exception) {
            dataOrException.e = e
        }

        return dataOrException
    }

//    suspend fun getTitleInfo(titlId:String): DataOrException<ETitle, Boolean, Exception> {
//        val response = try{
//            dataOrException.loading = true
////            gettitleifo =
//        }
//    }
}
package com.example.forumos.postinsert

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forumos.api.Constants.Companion.BASE_URL
import com.example.forumos.api.IPostAPI
import com.example.forumos.model.ForumosPost
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertPostViewModel : ViewModel() {
    val successInsertion = MutableLiveData<Boolean>()

    fun insertPost(newPost : ForumosPost) {

        val apiService: IPostAPI by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IPostAPI::class.java)
        }

        viewModelScope.launch {
            try {
                val response = apiService.createPost(newPost)
                if (response.isSuccessful) {
                    val result = response.body()
                    successInsertion.value = true
                } else {
                    successInsertion.value = false
                }
            } catch (e: Exception) {
                Log.e("upss do servidor", e.message ?: "");
                successInsertion.value = false
            }
        }
    }
}
package com.example.forumos.postdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forumos.api.Constants.Companion.BASE_URL
import com.example.forumos.api.IPostAPI
import com.example.forumos.model.ForumosPost
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailViewModel : ViewModel() {
    val post = MutableLiveData<ForumosPost>()

    fun fetchPost(postId : Int) {
        val apiService: IPostAPI by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IPostAPI::class.java)
        }

        viewModelScope.launch {
            apiService.getPost(postId).enqueue(object : Callback<ForumosPost> {
                override fun onResponse(call: Call<ForumosPost>, response: Response<ForumosPost>) {
                    post.value = response.body()
                }

                override fun onFailure(call: Call<ForumosPost>, t: Throwable) {
                    // Handle failure
                }
            })
        }

    }



}
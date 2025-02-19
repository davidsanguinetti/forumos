package com.example.forumos.listposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forumos.api.Constants.Companion.BASE_URL
import com.example.forumos.api.IPostAPI
import com.example.forumos.model.ForumosPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class ListFragmentViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<ForumosPost>>()
    val posts: MutableLiveData<List<ForumosPost>> = _posts

    fun getPosts() {
        val apiService: IPostAPI by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IPostAPI::class.java)
        }

        apiService.getPostList(1).enqueue(object : Callback<List<ForumosPost>> {
            override fun onResponse(p0: Call<List<ForumosPost>>, p1: Response<List<ForumosPost>>) {
                posts.value = p1.body()
            }

            override fun onFailure(p0: Call<List<ForumosPost>>, p1: Throwable) {
            }

        })
    }
}
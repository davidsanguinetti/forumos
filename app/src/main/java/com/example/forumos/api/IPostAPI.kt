package com.example.forumos.api

import com.example.forumos.model.ForumosPost
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IPostAPI {
    @GET("post/list/{parentpost_id}")
    fun getPostList(@Path("parentpost_id") number: Int): Call<List<ForumosPost>>

    @POST("post/add")
    suspend fun createPost(@Body requestBody: ForumosPost): Response<Any>

    @GET("post/id/{id}")
    fun getPost(@Path("id") number: Int) : Call<ForumosPost>
}

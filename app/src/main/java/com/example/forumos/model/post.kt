package com.example.forumos.model

import java.sql.Timestamp

data class ForumosPost (
    val titulo: String,
    val id_post: Int,
    val mensagem: String? = null,
    val autor: String,
    var id_parentpost: Int = 1,
    val timestamp: String = "",
    val respostas: List<ForumosPost> = listOf()
)

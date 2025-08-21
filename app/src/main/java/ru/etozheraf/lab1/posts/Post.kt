package ru.etozheraf.lab1.posts

data class Post(
    val uuid: String,
    val userUuid: String,
    val imgUrl: String? = null,
    val message: String,

    var isLiked: Boolean = false,
    var likes: Int = 0,
    var comments: Int = 0,
)
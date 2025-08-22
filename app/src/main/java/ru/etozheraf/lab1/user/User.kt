package ru.etozheraf.lab1.user

import ru.etozheraf.lab1.posts.Post

data class User(
    val uuid: String,
    val login: String,
    val name: String,
    val avatarUrl: String,
    var followers: Int,
    var isFollowing: Boolean = false,
    val following: Int,

    var posts: List<Post>,
)
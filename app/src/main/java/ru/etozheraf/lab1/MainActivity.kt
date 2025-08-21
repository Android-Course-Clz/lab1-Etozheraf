package ru.etozheraf.lab1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import ru.etozheraf.lab1.posts.Post
import ru.etozheraf.lab1.posts.PostAdapter
import ru.etozheraf.lab1.user.User

class MainActivity : AppCompatActivity() {
    private val user = User(
        uuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
        login = "johndoe",
        name = "John Doe",
        avatarUrl = "https://randomuser.me/api/portraits/men/52.jpg",
        followers = 52,
        isFollowing = false,
        following = 2,

        posts = mutableListOf(
            Post(
                uuid = "6c2d8a4a-2b77-4d46-9c78-5e7d74bb81d4",
                userUuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
                message = "Экспериментирую с новым рецептом \uD83C\uDF5D\n" +
                        "Оказалось, что готовить дома не так сложно, как кажется.\n" +
                        "Кто ещё любит кулинарные эксперименты? \uD83D\uDC69\u200D\uD83C\uDF73\n" +
                        "#foodie #homemade #yummy",
                imgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXIhuB3l4lSxyUbou7Xqv7XrUG9vB1NUjffw&s",
                likes = 89,
                comments = 12
            ),
            Post(
                uuid = "b3f63df6-59f2-4b84-870b-7f8f2c55f6fd",
                userUuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
                message = "Маленький шаг каждый день ведёт к большой цели \uD83D\uDE80\n" +
                        "Главное — не останавливаться и верить в себя.\n" +
                        "#motivation #progress #dreambig",
                imgUrl = null,
                likes = 63,
                comments = 37
            ),
            Post(
                uuid = "dc45e8a0-0f71-44b6-bd8f-3e8fef2ea21e",
                userUuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
                message = "Сегодня наконец-то выбрался на прогулку в парк \uD83C\uDF3F\n" +
                        "Свежий воздух + тёплое солнце = идеальное настроение ☀\uFE0F\n" +
                        "#relax #nature #happiness",
                imgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmxFkhW_uZewqu_80S-KnKPoijE7uytxRahQ&s",
                likes = 4,
                comments = 63
            ),
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        var avatar: CircleImageView = findViewById(R.id.user_avatar)
        Glide.with(this)
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_like)
            .into(avatar)

        var name: TextView = findViewById(R.id.user_name)
        name.text = user.name
        var login: TextView = findViewById(R.id.user_login)
        login.text = "@${user.login}"
        var postsCount: TextView = findViewById(R.id.user_posts_number)
        postsCount.text = user.posts.size.toString()
        var followersCount: TextView = findViewById(R.id.user_followers_number)
        followersCount.text = user.followers.toString()
        var followingCount: TextView = findViewById(R.id.user_following_number)
        followingCount.text = user.following.toString()

        var subscribeButton: Button = findViewById(R.id.user_subscribe)
        if (user.isFollowing) {
            subscribeButton.text = "Following"
        } else {
            subscribeButton.text = "Follow"
        }
        subscribeButton.setOnClickListener {
            user.isFollowing = !user.isFollowing
            if (user.isFollowing) {
                ++user.followers
                followersCount.text = user.followers.toString()
                subscribeButton.text = "Follow"
            } else {
                --user.followers
                followersCount.text = user.followers.toString()
                subscribeButton.text = "Following"
            }

            Toast.makeText(
                this,
                if (user.isFollowing) "Following ${user.login}" else "Unfollowed ${user.login}",
                Toast.LENGTH_SHORT
            ).show()
        }

        var btnMessage: Button = findViewById(R.id.user_send_message)
        btnMessage.setOnClickListener {
            Toast.makeText(this, "Message ${user.login}", Toast.LENGTH_SHORT).show()
        }

        var postsRecyclerView: RecyclerView = findViewById(R.id.posts_recycler_view)
        var postAdapter = PostAdapter(
            user.posts,
            onLikeClick = { post ->
                post.isLiked = !post.isLiked
                if (post.isLiked) {
                    ++post.likes
                } else {
                    --post.likes
                }
            },
            onCommentClick = {
                Toast.makeText(this, "Comment on post", Toast.LENGTH_SHORT).show()
            }
        )
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postAdapter
    }
}
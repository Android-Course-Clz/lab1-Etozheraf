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
        login = "@john_doe",
        name = "John Doe",
        avatarUrl = "https://randomuser.me/api/portraits/men/52.jpg",
        followers = 52,
        isFollowing = false,
        following = 2,

        posts = listOf(
            Post(
                uuid = "6c2d8a4a-2b77-4d46-9c78-5e7d74bb81d4",
                userUuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
                message = "Experimenting with a new recipe 🍝\n" +
                        "Turns out cooking at home isn’t as hard as it seems.\n" +
                        "Who else loves culinary experiments? 👩‍🍳\n" +
                        "#foodie #homemade #yummy",
                imgUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXIhuB3l4lSxyUbou7Xqv7XrUG9vB1NUjffw&s",
                likes = 89,
                comments = 12
            ),
            Post(
                uuid = "b3f63df6-59f2-4b84-870b-7f8f2c55f6fd",
                userUuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
                message = "A small step every day leads to a big goal 🚀\n" +
                        "The main thing is not to stop and to believe in yourself.\n" +
                        "#motivation #progress #dreambig",
                imgUrl = null,
                likes = 63,
                comments = 37
            ),
            Post(
                uuid = "dc45e8a0-0f71-44b6-bd8f-3e8fef2ea21e",
                userUuid = "a6e43e65-ff0f-41a3-b11a-62cb9b45792f",
                message = "Finally went for a walk in the park today 🌿\n" +
                        "Fresh air + warm sunshine = perfect mood ☀️\n" +
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
            .placeholder(R.drawable.avatar_placeholder)
            .error(R.drawable.avatar_placeholder)
            .into(avatar)

        var name: TextView = findViewById(R.id.user_name)
        name.text = user.name
        var login: TextView = findViewById(R.id.user_login)
        login.text = user.login
        var postsCount: TextView = findViewById(R.id.user_posts_number)
        postsCount.text = user.posts.size.toString()
        var followersCount: TextView = findViewById(R.id.user_followers_number)
        followersCount.text = user.followers.toString()
        var followingCount: TextView = findViewById(R.id.user_following_number)
        followingCount.text = user.following.toString()

        var subscribeButton: Button = findViewById(R.id.user_subscribe)
        if (user.isFollowing) {
            subscribeButton.setText(R.string.following)
        } else {
            subscribeButton.setText(R.string.follow)
        }
        subscribeButton.setOnClickListener {
            user.isFollowing = !user.isFollowing
            if (user.isFollowing) {
                ++user.followers
                followersCount.text = user.followers.toString()
                subscribeButton.setText(R.string.following)
            } else {
                --user.followers
                followersCount.text = user.followers.toString()
                subscribeButton.setText(R.string.follow)
            }

            Toast.makeText(
                this,
                if (user.isFollowing)
                    getString(R.string.toast_following, user.login)
                else
                    getString(R.string.toast_unfollowed, user.login),
                Toast.LENGTH_SHORT
            ).show()
        }

        var btnMessage: Button = findViewById(R.id.user_send_message)
        btnMessage.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_message, user.login), Toast.LENGTH_SHORT)
                .show()
        }

        var postsRecyclerView: RecyclerView = findViewById(R.id.posts_recycler_view)
        var postAdapter = PostAdapter(
            { post ->
                post.isLiked = !post.isLiked
                if (post.isLiked) {
                    ++post.likes
                } else {
                    --post.likes
                }
            },
            {
                Toast.makeText(this, getString(R.string.toast_comment), Toast.LENGTH_SHORT).show()
            }
        )
        postAdapter.submitList(user.posts)

        val viewLayoutManager = LinearLayoutManager(this)
        postsRecyclerView.apply {
            layoutManager = viewLayoutManager
            adapter = postAdapter
        }
    }
}
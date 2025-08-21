package ru.etozheraf.lab1.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.etozheraf.lab1.R

class PostAdapter(
    private var posts: List<Post>,
    private val onLikeClick: ((Post) -> Unit)? = null,
    private val onCommentClick: ((Post) -> Unit)? = null
) : RecyclerView.Adapter<PostViewHolder>() {
    fun setPosts(newPosts: List<Post>) {
        val diffResult = DiffUtil.calculateDiff(PostDiffUtilCallback(posts, newPosts))
        posts = newPosts
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val holder = PostViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_post, parent, false),
            onLikeClick,
            onCommentClick
        )

        return holder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
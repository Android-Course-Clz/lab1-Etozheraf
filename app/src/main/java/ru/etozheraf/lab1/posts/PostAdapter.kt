package ru.etozheraf.lab1.posts

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.etozheraf.lab1.R

class PostAdapter(
    private val onLikeClick: ((Post) -> Unit)? = null,
    private val onCommentClick: ((Post) -> Unit)? = null
) : ListAdapter<Post, PostViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val holder = PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )

        holder.buttonLike.setOnClickListener {
            val post = getItem(holder.bindingAdapterPosition)
            onLikeClick?.invoke(post)

            if (post.isLiked) {
                holder.buttonLike.iconTint =
                    ColorStateList.valueOf(holder.itemView.context.getColor(android.R.color.holo_red_light))
            } else {
                holder.buttonLike.iconTint =
                    ColorStateList.valueOf(holder.itemView.context.getColor(android.R.color.black))
            }
            holder.buttonLike.text = post.likes.toString()
        }

        holder.buttonComment.setOnClickListener {
            val post = getItem(holder.bindingAdapterPosition)
            onCommentClick?.invoke(post)
            holder.buttonComment.text = post.comments.toString()
        }

        return holder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

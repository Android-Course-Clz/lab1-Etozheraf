package ru.etozheraf.lab1.posts

import android.annotation.SuppressLint
import android.view.View
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.etozheraf.lab1.R
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton


class PostViewHolder(
    root: View
) : RecyclerView.ViewHolder(root) {
    val text: TextView = root.findViewById(R.id.post_text)
    val image: ImageView = root.findViewById(R.id.post_image)
    val buttonLike: MaterialButton = root.findViewById(R.id.btn_like)
    val buttonComment: MaterialButton = root.findViewById(R.id.btn_comment)

    @SuppressLint("SetTextI18n")
    fun bind(post: Post) {
        text.text = post.message

        if (post.imgUrl != null) {
            image.visibility = View.VISIBLE

            Glide.with(itemView.context)
                .load(post.imgUrl)
                .into(image)
        } else {
            image.visibility = View.GONE
        }

        buttonLike.text = post.likes.toString()

        if (post.isLiked) {
            buttonLike.iconTint =
                ColorStateList.valueOf(itemView.context.getColor(android.R.color.holo_red_light))
        } else {
            buttonLike.iconTint =
                ColorStateList.valueOf(itemView.context.getColor(android.R.color.darker_gray))
        }

        buttonComment.text = post.comments.toString()
    }
}
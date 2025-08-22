package ru.etozheraf.lab1.posts

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) =
        oldItem.uuid == newItem.uuid

    override fun areContentsTheSame(oldItem: Post, newItem: Post) =
        oldItem == newItem
}
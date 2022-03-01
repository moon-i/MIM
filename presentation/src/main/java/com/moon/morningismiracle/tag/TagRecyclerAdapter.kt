package com.moon.morningismiracle.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.databinding.ItemTagBinding
import com.moon.morningismiracle.setDrawableTint

class TagRecyclerAdapter: ListAdapter<TagModel, TagRecyclerAdapter.TagViewHolder>(tagDiffUtil) {

    inner class TagViewHolder(private val binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TagModel) {
            binding.tagNameTextView.text = item.tagName
            binding.tagColorImageView.drawable.setDrawableTint(item.tagColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun setData(datas: List<TagModel>) {
        submitList(datas)
    }

    companion object {
        val tagDiffUtil = object: DiffUtil.ItemCallback<TagModel>() {
            override fun areItemsTheSame(oldItem: TagModel, newItem: TagModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TagModel, newItem: TagModel): Boolean {
                return oldItem.tagId == newItem.tagId
            }
        }
    }
}
package com.moon.morningismiracle.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.TagStatisticsModel
import com.moon.morningismiracle.databinding.ItemStatisticsTagBinding
import com.moon.morningismiracle.setDrawableTint

class TagStatisticsAdapter: ListAdapter<TagStatisticsModel, TagStatisticsAdapter.TagStatisticsViewHolder>(tagDiffUtil) {

    inner class TagStatisticsViewHolder(private val binding: ItemStatisticsTagBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TagStatisticsModel) {
            binding.tagNameTextView.text = item.tagName
            binding.tagTotalCount.text = "총 ${item.totalCount}건"
            binding.tagSuccessCount.text = "성공 ${item.successCount}건"
            binding.itemBackground.background.setDrawableTint(item.tagColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagStatisticsViewHolder {
        return TagStatisticsViewHolder(ItemStatisticsTagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TagStatisticsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun setData(datas: List<TagStatisticsModel>) {
        submitList(datas)
    }

    companion object {
        val tagDiffUtil = object: DiffUtil.ItemCallback<TagStatisticsModel>() {
            override fun areItemsTheSame(oldItem: TagStatisticsModel, newItem: TagStatisticsModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TagStatisticsModel, newItem: TagStatisticsModel): Boolean {
                return oldItem.tagId == newItem.tagId
            }
        }
    }
}
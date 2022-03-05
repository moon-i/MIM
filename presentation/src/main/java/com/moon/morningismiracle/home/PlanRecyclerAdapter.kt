package com.moon.morningismiracle.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.PlanModel
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.ItemPlanBinding
import com.moon.morningismiracle.setDrawableTint

class PlanRecyclerAdapter : ListAdapter<PlanModel, PlanRecyclerAdapter.PlanViewHolder>(planDiffUtil) {
    private var context: Context? = null

    inner class PlanViewHolder(private val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlanModel) {
            binding.planNameTextView.text = item.planName
            item.planTag?.let { tag ->
                binding.tagNameTextView.visibility = View.VISIBLE
                binding.tabImageView.visibility = View.VISIBLE
                binding.tagNameTextView.text = tag.tagName
                binding.tabImageView.drawable.setDrawableTint(tag.tagColor)
            } ?: run {
                binding.tagNameTextView.visibility = View.GONE
                binding.tabImageView.visibility = View.GONE
            }

            when(item.planState) {
                "SUCCESS" -> {
                    context?.let {
                        binding.itemBackgroundView.background.setTint(ContextCompat.getColor(it, R.color.background_light_grey))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        context = parent.context
        return PlanViewHolder(ItemPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun setData(datas: List<PlanModel>) {
        submitList(datas)
    }

    companion object {
        val planDiffUtil = object : DiffUtil.ItemCallback<PlanModel>() {
            override fun areContentsTheSame(oldItem: PlanModel, newItem: PlanModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: PlanModel, newItem: PlanModel): Boolean {
                return oldItem.planId == newItem.planId
            }
        }
    }
}
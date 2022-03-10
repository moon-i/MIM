package com.moon.morningismiracle.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.ItemTagInPlanBottomSheetBinding
import com.moon.morningismiracle.setDrawableTint

class PlanAddTagAdapter : ListAdapter<TagModel, PlanAddTagAdapter.TagViewHolder>(planDiffUtil) {
    var onTagSelect: ((Long) -> Unit)? = null

    inner class TagViewHolder(private val binding: ItemTagInPlanBottomSheetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TagModel) {
            with(binding) {
                if (item.tagId == -1L) {
                    tagNoneImageView.visibility = View.VISIBLE
                    tagImageView.visibility = View.GONE
                    tagNameTextView.text = binding.root.context.getString(R.string.nonTagText)
                } else {
                    tagNoneImageView.visibility = View.GONE
                    tagImageView.visibility = View.VISIBLE
                    tagNameTextView.text = item.tagName
                    tagImageView.drawable.setDrawableTint(item.tagColor)
                }

                if (item.tagSelected) {
                    if (item.tagColor.isNotBlank()) {
                        selectBackground.background.setDrawableTint(item.tagColor)
                    } else {
                        selectBackground.background.setDrawableTint(
                            R.color.background_light_grey,
                            root.context
                        )
                    }
                } else {
                    selectBackground.background.setDrawableTint(
                        R.color.white,
                        root.context
                    )
                }
                selectBackground.setOnClickListener {
                    onTagSelect?.invoke(item.tagId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            ItemTagInPlanBottomSheetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun setData(datas: List<TagModel>) {
        submitList(datas)
        notifyDataSetChanged()
    }

    companion object {
        val planDiffUtil = object : DiffUtil.ItemCallback<TagModel>() {
            override fun areItemsTheSame(oldItem: TagModel, newItem: TagModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TagModel, newItem: TagModel): Boolean {
                return oldItem.tagId == newItem.tagId
            }
        }
    }
}
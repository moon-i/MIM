package com.moon.morningismiracle.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.PlanModel
import com.moon.morningismiracle.databinding.ItemPlanInCalendarTabBinding
import com.moon.morningismiracle.setDrawableTint

class PlanAdapter: ListAdapter<PlanModel, PlanAdapter.PlanViewHolder>(planDiffUtil) {

    inner class PlanViewHolder(private val binding: ItemPlanInCalendarTabBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlanModel) {
            with(binding) {
                planNameTextView.text = item.planName
                planStateTextView.text = item.planState // TODO Enum으로 빼서 상태 정보 mapping하기
                // TODO planStateBox.background.setTint()
                item.planTag?.let { tag ->
                    planTagTextView.visibility = View.VISIBLE
                    tagColorView.visibility = View.VISIBLE
                    planTagTextView.text = tag.tagName
                    tagColorView.background.setDrawableTint(tag.tagColor)
                } ?: run {
                    planTagTextView.visibility = View.GONE
                    tagColorView.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder(ItemPlanInCalendarTabBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun setData(datas: List<PlanModel>) {
        submitList(datas)
    }

    companion object {
        val planDiffUtil = object: DiffUtil.ItemCallback<PlanModel>() {
            override fun areItemsTheSame(oldItem: PlanModel, newItem: PlanModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: PlanModel, newItem: PlanModel): Boolean {
                return oldItem.planId == newItem.planId
            }
        }
    }
}
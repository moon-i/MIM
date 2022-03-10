package com.moon.morningismiracle.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.ItemPlanInCalendarTabBinding
import com.moon.morningismiracle.setDrawableTint

class PlanAdapter: ListAdapter<PlanModel, PlanAdapter.PlanViewHolder>(planDiffUtil) {

    inner class PlanViewHolder(private val binding: ItemPlanInCalendarTabBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlanModel) {
            with(binding) {
                planNameTextView.text = item.planName
                setStateUi(item.planState)
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

        private fun setStateUi(state: PlanState) {
            binding.planStateTextView.text = state.uiValue // TODO Enum으로 빼서 상태 정보 mapping하기
            when (state) {
                PlanState.SUCCESS -> binding.planStateBox.background.setDrawableTint(R.color.main_4, binding.root.context)
                PlanState.FAIL -> binding.planStateBox.background.setDrawableTint(R.color.main_1, binding.root.context)
                PlanState.WAITING -> binding.planStateBox.background.setDrawableTint(R.color.background_grey, binding.root.context)
                PlanState.CANCEL -> binding.planStateBox.background.setDrawableTint(R.color.main_3, binding.root.context)
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
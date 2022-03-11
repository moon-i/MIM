package com.moon.morningismiracle.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.ItemPlanInCalendarTabBinding
import com.moon.morningismiracle.di.DateInfo
import com.moon.morningismiracle.setDrawableTint

class PlanAdapter: ListAdapter<PlanModel, PlanAdapter.PlanViewHolder>(planDiffUtil) {
    var onDeleteBtn: ((PlanModel) -> Unit)? = null

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

                if (item.planDate < DateInfo.today) {
                    deleteBtn.visibility = View.GONE
                } else {
                    deleteBtn.visibility = View.VISIBLE
                    deleteBtn.setOnClickListener {
                        onDeleteBtn?.invoke(item)
                    }
                }
            }
        }

        private fun setStateUi(state: PlanState) {
            binding.planStateTextView.text = state.uiValue // TODO Enum으로 빼서 상태 정보 mapping하기
            when (state) {
                PlanState.SUCCESS -> {
                    binding.planStateBox.background.setDrawableTint(R.color.plan_success, binding.root.context)
                    binding.planStateTextView.setTextColor(getColor(binding.root.context, R.color.main_4))
                }
                PlanState.FAIL -> {
                    binding.planStateBox.background.setDrawableTint(R.color.plan_cancel, binding.root.context)
                    binding.planStateTextView.setTextColor(getColor(binding.root.context, R.color.main_1))
                }
                PlanState.WAITING -> {
                    binding.planStateBox.background.setDrawableTint(R.color.background_light_grey, binding.root.context)
                    binding.planStateTextView.setTextColor(getColor(binding.root.context, R.color.background_grey))

                }
                PlanState.CANCEL -> {
                    binding.planStateBox.background.setDrawableTint(R.color.plan_fail, binding.root.context)
                    binding.planStateTextView.setTextColor(getColor(binding.root.context, R.color.main_3))
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
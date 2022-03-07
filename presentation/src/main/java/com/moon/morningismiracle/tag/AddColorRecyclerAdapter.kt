package com.moon.morningismiracle.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.ColorPaletteViewBinding
import com.moon.morningismiracle.setDrawableTint

class AddColorRecyclerAdapter : ListAdapter<AddColorRecyclerAdapter.ColorSet, AddColorRecyclerAdapter.AddColorRecyclerViewHolder>(diffUtil) {
    var onColorClick: ((Int) -> Unit)? = null
    data class ColorSet(var color: String, var isSelected: Boolean)

    inner class AddColorRecyclerViewHolder(private val binding: ColorPaletteViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ColorSet, position: Int) {
            binding.colorView.background.setDrawableTint(data.color)
            binding.colorSelectView.setOnClickListener { onColorClick?.invoke(position) }

            if (data.isSelected) {
                binding.colorSelectView.background.setDrawableTint(R.color.background_grey, binding.root.context)
            } else {
                binding.colorSelectView.background.setDrawableTint(R.color.background_light_grey, binding.root.context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddColorRecyclerViewHolder {
        return AddColorRecyclerViewHolder(ColorPaletteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AddColorRecyclerViewHolder, position: Int) {
        holder.bind(currentList[position], position)
    }

    fun setData(data: List<ColorSet>) {
        submitList(data)
        notifyDataSetChanged()
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<ColorSet>() {
            override fun areItemsTheSame(oldItem: ColorSet, newItem: ColorSet): Boolean {
                return (oldItem.color == newItem.color && oldItem.isSelected == newItem.isSelected)
            }

            override fun areContentsTheSame(oldItem: ColorSet, newItem: ColorSet): Boolean {
                return oldItem == newItem
            }
        }
    }
}
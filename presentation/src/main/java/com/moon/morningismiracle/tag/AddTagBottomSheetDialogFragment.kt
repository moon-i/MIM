package com.moon.morningismiracle.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.colorResourceToString
import com.moon.morningismiracle.databinding.BottomSheetAddTagBinding
import com.moon.morningismiracle.tag.AddColorRecyclerAdapter.ColorSet

class AddTagBottomSheetDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetAddTagBinding
    private val colorListAdapter by lazy { AddColorRecyclerAdapter() }

    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    private var colorList: MutableList<ColorSet> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetAddTagBinding.inflate(inflater, container, false)
        bottomSheetBehavior = (dialog as? BottomSheetDialog)?.behavior
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initColorList()
        initView()
    }

    private fun initView() {
        binding.colorPaletteRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = colorListAdapter
            colorListAdapter.onColorClick = ::onColorClick
            colorListAdapter.setData(colorList)
        }
    }

    private fun onColorClick(position: Int) {
        colorList.map { item ->
            item.isSelected = false
        }
        colorList[position].isSelected = true
        colorListAdapter.setData(colorList)
    }

    private fun initColorList() {
        colorList = mutableListOf(
            ColorSet(colorResourceToString(R.color.main_1, requireContext()), true),
            ColorSet(colorResourceToString(R.color.main_2, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_3, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_4, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_5, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_6, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_7, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_8, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_9, requireContext()), false),
            ColorSet(colorResourceToString(R.color.main_10, requireContext()), false),
        )
    }

    companion object {
        const val TAG = "AddTagBottomSheetDialogFragment"
    }
}
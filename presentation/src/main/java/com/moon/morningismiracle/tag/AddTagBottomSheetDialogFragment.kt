package com.moon.morningismiracle.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.R
import com.moon.morningismiracle.colorResourceToString
import com.moon.morningismiracle.databinding.BottomSheetAddTagBinding
import com.moon.morningismiracle.tag.AddColorRecyclerAdapter.ColorSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTagBottomSheetDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetAddTagBinding
    private val tagViewModel: TagViewModel by viewModels()

    private val colorListAdapter by lazy { AddColorRecyclerAdapter() }

    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    private var colorList: MutableList<ColorSet> = mutableListOf()
    private var tagData: TagModel = TagModel(0, "", "", true)

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

        arguments?.getBoolean(ARGS_KEY_CHANGE_TAG_MODE)?.let { mode ->
            if (mode) {
                arguments?.let { args ->
                    val paramData = args.getSerializable(ARGS_KEY_TAG_DATA) as TagModel
                    tagData = paramData
                    initColorList(tagData.tagColor)
                    initViewChangeMode(tagData.tagName)
                }
            } else {
                initColorList()
                initViewAddMode()
            }
        }
    }

    private fun initViewAddMode() {
        initColorPalette()

        binding.addTagBtn.setOnClickListener {
            binding.tagInputField.editText?.let { editText ->
                if (editText.text.isEmpty()) {
                    // Set error text
                    binding.tagInputField.error = getString(R.string.noTagName)
                } else {
                    binding.tagInputField.error = null
                    tagData.tagName = editText.text.toString()
                    tagViewModel.addTag(tagData)
                    dialog?.dismiss()
                }
            }
        }
    }

    private fun initViewChangeMode(tagName: String) {
        initColorPalette()
        with(binding) {
            tagInputField.editText?.setText(tagName)
            tagInputField.isEnabled = false

            addTagBottomSheetTitle.text = requireContext().getString(R.string.changeTagButtonText)
            addTagBtn.text = requireContext().getString(R.string.changeTagButtonText)
            addTagBtn.setOnClickListener {
                tagViewModel.updateTagColor(tagData.tagId, tagData.tagColor)
                dialog?.dismiss()
            }
        }
    }

    private fun initColorPalette() {
        binding.colorPaletteRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = colorListAdapter
            colorListAdapter.onColorClick = ::onColorClick
            colorListAdapter.setData(colorList)
            itemAnimator = null
        }
    }

    private fun onColorClick(position: Int) {
        colorList.map { item ->
            item.isSelected = false
        }
        colorList[position].isSelected = true
        tagData.tagColor = colorList[position].color
        colorListAdapter.setData(colorList)
    }

    private fun initColorList(color: String? = null) {
        colorList = mutableListOf(
            ColorSet(colorResourceToString(R.color.main_1, requireContext()), false),
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
        color?.let { color ->
            colorList.mapIndexed { index, colorSet ->
                if (colorSet.color == color) {
                    colorList[index].isSelected = true
                    tagData.tagColor = colorList[index].color
                }
            }
        } ?: run {
            colorList[0].isSelected = true
            tagData.tagColor = colorList[0].color
        }
    }

    companion object {
        const val TAG = "AddTagBottomSheetDialogFragment"

        fun getInstance(changeTagMode: Boolean, tagData: TagModel? = null): AddTagBottomSheetDialogFragment {
            val newFragment = AddTagBottomSheetDialogFragment()
            val args = Bundle().apply {
                putBoolean(ARGS_KEY_CHANGE_TAG_MODE, changeTagMode)
                putSerializable(ARGS_KEY_TAG_DATA, tagData)
            }
            newFragment.arguments = args
            return newFragment
        }

        const val ARGS_KEY_CHANGE_TAG_MODE = "ARGS_KEY_CHANGE_TAG_MODE"
        const val ARGS_KEY_TAG_DATA = "ARGS_KEY_TAG_DATA"
    }
}
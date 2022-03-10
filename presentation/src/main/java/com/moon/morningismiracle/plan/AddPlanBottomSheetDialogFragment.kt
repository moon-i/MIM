package com.moon.morningismiracle.plan

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
import com.moon.domain.model.PlanModel
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.databinding.BottomSheetAddPlanBinding
import com.moon.morningismiracle.tag.TagViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddPlanBottomSheetDialogFragment: BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetAddPlanBinding
    private val tagViewModel: TagViewModel by viewModels()

    private val planAddTagAdapter by lazy { PlanAddTagAdapter() }

    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    private var planData: PlanModel = PlanModel(0, "", "", Date(), null)
    private var nonTagItem = TagModel(-1L, "", "", true)
    private var mockList: MutableList<TagModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetAddPlanBinding.inflate(inflater, container, false)
        bottomSheetBehavior = (dialog as? BottomSheetDialog)?.behavior
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTagList()
        initView()
    }

    private fun initView() {
        arguments?.getString(ARGS_KEY_DATE_DATA)?.let { title ->
            // TODO - 실제 데이터로 변경
            binding.addPlanBottomSheetTitle.text = title
        }
    }

    private fun initTagList() {
        binding.selectTagRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = planAddTagAdapter
            planAddTagAdapter.onTagSelect = ::onTagSelect
        }

        // TODO - 실제 데이터로 변경
        mockList = mutableListOf(
            nonTagItem,
            TagModel(1L, "운동", "#E8C26B", false),
            TagModel(2L, "동구박", "#A3CF77", false),
            TagModel(3L, "과수원길", "#59C8AF", false),
            TagModel(4L, "아카시아꽃이", "#E8C26B", false),
            TagModel(1L, "운동", "#E8C26B", false),
            TagModel(2L, "동구박", "#A3CF77", false),
        )
        planAddTagAdapter.setData(
            mockList!!
        )
    }

    private fun onTagSelect(tagId: Long) {
        mockList?.mapIndexed { i, tag ->
            mockList?.get(i)?.tagState = tag.tagId == tagId
        }
        planAddTagAdapter.setData(mockList!!)
    }

    companion object {
        const val TAG = "AddPlanBottomSheetDialogFragment"

        fun newInstance(date: String): AddPlanBottomSheetDialogFragment {
            val newFragment = AddPlanBottomSheetDialogFragment()
            val args = Bundle().apply {
                putString(ARGS_KEY_DATE_DATA, date)
            }
            newFragment.arguments = args
            return newFragment
        }

        const val ARGS_KEY_DATE_DATA = "ARGS_KEY_DATE_DATA"
    }

}
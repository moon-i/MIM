package com.moon.morningismiracle.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.BottomSheetAddPlanBinding
import com.moon.morningismiracle.tag.TagViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddPlanBottomSheetDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetAddPlanBinding
    private val tagViewModel: TagViewModel by viewModels()
    private val planViewModel: PlanViewModel by viewModels()
    private val planAddTagAdapter by lazy { PlanAddTagAdapter() }

    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null

    private var planData: PlanModel = PlanModel(0, "", PlanState.WAITING, Date(), null)
    private var nonTagItem = TagModel(-1L, "", "", true, true)
    private var tagList: MutableList<TagModel>? = null
    private var chooseDate: Date? = null

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
        tagViewModel.getTagList()
        setObserver()
    }

    private fun initView() {
        arguments?.getParcelable<CalendarDay>(ARGS_KEY_DATE_DATA)?.let { date ->
            chooseDate = date.date
            binding.addPlanBottomSheetTitle.text =
                "${date.year}년 ${date.month + 1}월 ${date.day}일 계획 추가하기"
        }

        binding.addPlanBtn.setOnClickListener {
            binding.plaInputField.editText?.let { editText ->
                if (editText.text.isEmpty()) {
                    binding.plaInputField.error = getString(R.string.noTagName)
                } else {
                    binding.plaInputField.error = null
                    planData.planName = editText.text.toString()
                    chooseDate?.let { date -> planData.planDate = date }
                    planViewModel.addPlan(planData)
                    dialog?.dismiss()
                }
            }
        }
    }

    private fun initTagList() {
        binding.selectTagRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = planAddTagAdapter
            planAddTagAdapter.onTagSelect = ::onTagSelect
        }
    }

    private fun onTagSelect(tagId: Long) {
        tagList?.let { list ->
            list.mapIndexed { i, tag ->
                list[i].tagSelected = tag.tagId == tagId
            }
            planAddTagAdapter.setData(list)
        }

        if (tagId == -1L) {
            planData.planTag = null
        } else {
            planData.planTag = TagModel(tagId = tagId, "", "", true)
        }
    }

    private fun setObserver() {
        tagViewModel.tagDataList.asLiveData().observe(this) {
            tagList = it.toMutableList()
            tagList?.let { list ->
                list.add(0, nonTagItem)
                planAddTagAdapter.setData(list)
            }
        }
    }

    companion object {
        const val TAG = "AddPlanBottomSheetDialogFragment"

        fun newInstance(date: CalendarDay): AddPlanBottomSheetDialogFragment {
            val newFragment = AddPlanBottomSheetDialogFragment()
            val args = Bundle().apply {
                putParcelable(ARGS_KEY_DATE_DATA, date)
            }
            newFragment.arguments = args
            return newFragment
        }

        const val ARGS_KEY_DATE_DATA = "ARGS_KEY_DATE_DATA"
    }

}
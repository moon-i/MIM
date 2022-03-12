package com.moon.morningismiracle.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.domain.model.PlanState
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentHomeBinding
import com.moon.morningismiracle.di.DateInfo
import com.moon.morningismiracle.plan.AddPlanBottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_home

    private val viewModel: HomeViewModel by viewModels()
    private val planAdapter = PlanRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkVisitLastTime()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observePlanList()
        getPlanData()
    }

    private fun initView() {
        binding.planTitleDate.text =
            String.format(
                getString(R.string.planTitleData),
                CalendarDay.from(DateInfo.today).year,
                CalendarDay.from(DateInfo.today).month + 1,
                CalendarDay.from(DateInfo.today).day
            )
        binding.planRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = planAdapter
            itemAnimator = null
            setHasFixedSize(true)
            planAdapter.apply {
                onSuccessClick = ::onSuccessClick
                onLaterClick = ::onLaterClick
                onCancelClick = ::onCancelClick
            }
        }
        binding.addPlanBtn.setOnClickListener {
            showAddBottomSheet()
        }
    }

    fun getPlanData() {
        viewModel.getPlanList(DateInfo.today)
    }

    private fun observePlanList() {
        viewModel.planDataList.asLiveData().observe(this) { list ->
            if (list.isEmpty()) {
                binding.planRecyclerView.visibility = View.GONE
                binding.noPlanImageView.visibility = View.VISIBLE
                binding.noPlanTextView.visibility = View.VISIBLE
            } else {
                planAdapter.setData(list)
                binding.planRecyclerView.visibility = View.VISIBLE
                binding.noPlanImageView.visibility = View.GONE
                binding.noPlanTextView.visibility = View.GONE
            }
        }
    }

    private fun checkVisitLastTime() {
        viewModel.setPlanStateBeforeToday(DateInfo.today)
    }

    private fun showAddBottomSheet() {
        val addPlanThatDayBS = AddPlanBottomSheetDialogFragment.newInstance(CalendarDay.from(DateInfo.today))
        addPlanThatDayBS.show(
            childFragmentManager,
            AddPlanBottomSheetDialogFragment.TAG
        )
    }

    private fun onSuccessClick(planId: Long, isSelect: Boolean) {
        if (!isSelect) { // 처음 선택하는 경우 success로
            viewModel.setPlan(planId, PlanState.SUCCESS)
        } else { // 이미 success가 선택되어있는경우 waiting으로 변경
            viewModel.setPlan(planId, PlanState.WAITING)
        }
    }

    private fun onLaterClick(planId: Long, isPossible: Boolean) {
        if (!isPossible) {
            Toast.makeText(requireContext(), getString(R.string.laterWarningText), Toast.LENGTH_SHORT).show()
        } else {
            viewModel.setPlanDelayOndDay(planId, DateInfo.tomorrow)
        }
    }

    private fun onCancelClick(planId: Long, isSelect: Boolean) {
        if (!isSelect) {
            viewModel.setPlan(planId, PlanState.CANCEL)
        } else {
            viewModel.setPlan(planId, PlanState.WAITING)
        }
    }
}
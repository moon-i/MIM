package com.moon.morningismiracle.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentHomeBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_home

    private val viewModel: HomeViewModel by viewModels()
    private val planAdapter = PlanRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getPlanData()
        observePlanList()
    }

    private fun initView() {
        binding.planRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = planAdapter
        }
    }

    private fun getPlanData() {
        viewModel.getPlanList(CalendarDay.today().date)
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
}
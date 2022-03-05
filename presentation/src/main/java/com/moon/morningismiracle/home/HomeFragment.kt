package com.moon.morningismiracle.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.domain.model.PlanModel
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_home
    private val planAdapter = PlanRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.planRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = planAdapter
        }

        planAdapter.setData(
            listOf(
                PlanModel(1, "밥먹기", "WAITING", "2022-02-12", TagModel(1, "먹고살기", "#DC5D6A", false)),
                PlanModel(2, "잠자기", "SUCCESS", "2022-02-12", TagModel(1, "먹고살기", "#DC5D6A", false)),
                PlanModel(3, "공부", "WAITING", "2022-02-12", TagModel(1, "공부", "#E8C26B", false)),
                PlanModel(4, "가나", "WAITING", "2022-02-12", TagModel(1, "궁금하다 궁금하다", "#59C8AF", false)),
                PlanModel(5, "디라", "WAITING", "2022-02-12"),
            )
        )
    }
}
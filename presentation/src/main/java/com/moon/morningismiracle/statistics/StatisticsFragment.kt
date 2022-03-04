package com.moon.morningismiracle.statistics

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.moon.domain.model.TagStatisticsModel
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentStatisticsBinding

class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_statistics
    private val tagStatisticsAdapter by lazy { TagStatisticsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.tagStatisticsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tagStatisticsAdapter
            isNestedScrollingEnabled = false
        }

        binding.totalWeekInfo.apply {
            infoLayoutTitle.text = "총"
            infoLayoutValue.text = "15건"
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_all)
        }

        binding.failWeekInfo.apply {
            infoLayoutTitle.text = "실패"
            infoLayoutValue.text = "1건"
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_fail)
        }

        binding.successWeekInfo.apply {
            infoLayoutTitle.text = "성공"
            infoLayoutValue.text = "4건"
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_success)
        }

        // TODO TagState false인것 안보이게
        // TODO 실제 데이터 연결하기
        tagStatisticsAdapter.setData(
            listOf(
                TagStatisticsModel(3, "요가", "#DC5D6A", 2, 3),
                TagStatisticsModel(4, "필테", "#E68765", 2, 3),
                TagStatisticsModel(5, "일", "#E8C26B", 5, 7),
                TagStatisticsModel(6, "먹기", "#A3CF77", 4, 7),
                TagStatisticsModel(7, "사시", "#59C8AF", 100, 3054),
                TagStatisticsModel(8, "정시", "#53ADCA", 23, 32),
                TagStatisticsModel(9, "토익", "#5A8CD5", 12, 4),
                TagStatisticsModel(10, "오픽", "#9E89D1", 1, 42)
            )
        )
    }
}
package com.moon.morningismiracle.tag

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentTagBinding

class TagFragment: BaseFragment<FragmentTagBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_tag

    private val tagAdapter: TagRecyclerAdapter by lazy { TagRecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tagRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tagAdapter
        }

        // TODO TagState false인것 안보이게
        // TODO 실제 데이터 연결하기
        tagAdapter.setData(listOf(
            TagModel(1, "공부", "#F36472", true),
            TagModel(2, "놀기", "#FA9673", true),
            TagModel(3, "요가", "#FBD972", true),
            TagModel(4, "필테", "#BCEF8A", true),
            TagModel(5, "일", "#7CE2CB", true),
            TagModel(6, "먹기", "#6CC7F1", true),
            TagModel(7, "사시", "#7DB1FD", true),
            TagModel(8, "정시", "#C5ADFF", true),
            TagModel(9, "토익", "#999DA1", true),
            TagModel(10, "오픽", "#e65160", true)
        ))

    }
}
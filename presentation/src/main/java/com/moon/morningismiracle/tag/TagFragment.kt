package com.moon.morningismiracle.tag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TagFragment: BaseFragment<FragmentTagBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_tag

    private val tagViewModel: TagViewModel by viewModels()

    private val tagAdapter: TagRecyclerAdapter by lazy { TagRecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
        setObserve()
    }

    private fun initView() {
        binding.tagRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tagAdapter
            tagAdapter.onDeleteClick = ::onDeleteClick
            tagAdapter.onListClick = ::onListClick
            itemAnimator = null
        }

        binding.addTagBtn.setOnClickListener {
            val addTagBottomSheet = AddTagBottomSheetDialogFragment.getInstance(false)
            addTagBottomSheet.show(childFragmentManager, AddTagBottomSheetDialogFragment.TAG)
        }
    }

    fun getData() {
        tagViewModel.getTagList()
    }

    private fun setObserve() {
        tagViewModel.tagDataList.asLiveData().observe(this) { list ->
            if (list.isEmpty()) {
                binding.noTagImageView.visibility = View.VISIBLE
                binding.noTagTextView.visibility = View.VISIBLE
                binding.tagRecyclerView.visibility = View.GONE
            } else {
                tagAdapter.setData(list)
                binding.noTagImageView.visibility = View.GONE
                binding.noTagTextView.visibility = View.GONE
                binding.tagRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun onDeleteClick(tagModel: TagModel) {
        tagViewModel.deleteTag(tagModel.tagId)
    }

    private fun onListClick(tagModel: TagModel) {
        val updateBottomSheetDialogFragment =
            AddTagBottomSheetDialogFragment.getInstance(true, tagModel)
        updateBottomSheetDialogFragment.show(
            childFragmentManager,
            "${AddTagBottomSheetDialogFragment.TAG}${tagModel.tagId}"
        )
    }
}
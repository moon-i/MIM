package com.moon.morningismiracle

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.moon.morningismiracle.databinding.ActivityMainBinding
import com.moon.morningismiracle.home.HomeFragment
import com.moon.morningismiracle.plan.PlanFragment
import com.moon.morningismiracle.statistics.StatisticsFragment
import com.moon.morningismiracle.tag.TagFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override var layoutResourceId: Int = R.layout.activity_main

    private val homeFragment by lazy { HomeFragment() }
    private val planFragment by lazy { PlanFragment() }
    private val tagFragment by lazy { TagFragment() }
    private val statisticsFragment by lazy { StatisticsFragment() }

    private val itemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.pageHome -> {
                    switchFragment(homeFragment)
                    true
                }
                R.id.pagePlan -> {
                    switchFragment(planFragment)
                    true
                }
                R.id.pageTag -> {
                    switchFragment(tagFragment)
                    true
                }
                R.id.pageStatistics -> {
                    switchFragment(statisticsFragment)
                    true
                }
                else -> false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        switchFragment(homeFragment)
        binding.bottomNavigation.setOnItemSelectedListener(itemSelectedListener)
        binding.bottomNavigation.itemRippleColor = null
    }

    private fun switchFragment(fragment: Fragment) {
        // FragmentManager를 통해서 FragmentTransaction 획득하기
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView.id, fragment)
            setReorderingAllowed(true)
            commit()
        }
    }
}
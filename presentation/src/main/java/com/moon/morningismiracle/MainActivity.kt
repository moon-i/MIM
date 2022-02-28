package com.moon.morningismiracle

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.moon.morningismiracle.databinding.ActivityMainBinding
import com.moon.morningismiracle.home.HomeFragment
import com.moon.morningismiracle.plan.PlanFragment
import com.moon.morningismiracle.statistics.StatisticsFragment
import com.moon.morningismiracle.tag.TagFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override var layoutResourceId: Int = R.layout.activity_main

    val homeFragment by lazy { HomeFragment() }
    val planFragment by lazy { PlanFragment() }
    val tagFragment by lazy { TagFragment() }
    val statisticsFragment by lazy { StatisticsFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FragmentManager를 통해서 FragmentTransaction 획득하기
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView.id, homeFragment)
            setReorderingAllowed(true)
            commit()
        }
    }
}
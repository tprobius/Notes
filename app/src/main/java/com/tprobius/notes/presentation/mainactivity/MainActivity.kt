package com.tprobius.notes.presentation.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tprobius.notes.R
import com.tprobius.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tabTitles = arrayOf(
        R.string.tab_title_1,
        R.string.tab_title_2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = PagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.setText(tabTitles[position])
        }.attach()
    }
}
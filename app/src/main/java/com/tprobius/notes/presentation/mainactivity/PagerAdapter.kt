package com.tprobius.notes.presentation.mainactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tprobius.notes.presentation.favoritfragment.FavoriteFragment
import com.tprobius.notes.presentation.listfragment.ListFragment

private enum class TABS {
    LIST, FAVORITE
}

class PagerAdapter(fragmentManager: FragmentActivity) : FragmentStateAdapter(fragmentManager) {
    override fun getItemCount() = TABS.entries.size
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            TABS.LIST.ordinal -> ListFragment()
            TABS.FAVORITE.ordinal -> FavoriteFragment()
            else -> throw IllegalArgumentException()
        }
    }
}
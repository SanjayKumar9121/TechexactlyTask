package com.saveetha.techexactlytask.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.saveetha.techexactlytask.View.ApplicationsFragment
import com.saveetha.techexactlytask.View.SettingsFragment

class TabsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ApplicationsFragment()
            1 -> SettingsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}

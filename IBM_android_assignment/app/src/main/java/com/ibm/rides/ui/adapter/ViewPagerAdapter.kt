package com.ibm.rides.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibm.rides.ui.viewPager.CarbonEmissionDetailsFragment
import com.ibm.rides.ui.viewPager.VehicleDetailsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2 

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VehicleDetailsFragment()
            1 -> CarbonEmissionDetailsFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}

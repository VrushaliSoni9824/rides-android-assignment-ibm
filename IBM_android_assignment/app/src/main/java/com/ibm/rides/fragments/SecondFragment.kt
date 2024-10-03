package com.ibm.rides.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.VehicleViewModel
import com.ibm.rides.adapters.ViewPagerAdapter
import com.ibm.rides.databinding.FragmentSecondBinding
import com.ibm.rides.model.Vehicle

class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null

    private val sharedViewModel: VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSecondBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            endFragment = this@SecondFragment

            // Setup ViewPager2
            val viewPagerAdapter = ViewPagerAdapter(requireActivity())
            viewPager.adapter = viewPagerAdapter
        }
    }

    fun goHome() {
        // Reset order in view model
        sharedViewModel.resetVehicleData()

        findNavController().navigate(R.id.action_endFragment_to_startFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
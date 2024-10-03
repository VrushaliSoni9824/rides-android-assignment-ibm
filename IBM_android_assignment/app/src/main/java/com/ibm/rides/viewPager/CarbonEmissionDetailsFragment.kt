package com.ibm.rides.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ibm.rides.VehicleViewModel
import com.ibm.rides.databinding.FragmentCarbonEmmissionBinding

class CarbonEmissionDetailsFragment : Fragment() {

    private var binding: FragmentCarbonEmmissionBinding? = null
    private val sharedViewModel: VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCarbonEmmissionBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
        sharedViewModel.selectedVehicle.observe(viewLifecycleOwner) { vehicle ->
            vehicle?.let {
                binding?.co2EmissionsTextView?.text = vehicle.kilometrage.toString()
                val carbonEmissions = sharedViewModel.getCarbonEmissionsForSelectedVehicle()
                binding?.carbonEmissionsTextView?.text = carbonEmissions?.toString() ?: "N/A"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

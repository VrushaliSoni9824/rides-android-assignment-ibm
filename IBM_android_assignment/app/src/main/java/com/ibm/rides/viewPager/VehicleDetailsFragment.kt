package com.ibm.rides.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ibm.rides.VehicleViewModel
import com.ibm.rides.databinding.FragmentVehicleDetailsBinding

class VehicleDetailsFragment : Fragment() {

    private var binding: FragmentVehicleDetailsBinding? = null
    private val sharedViewModel: VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentVehicleDetailsBinding.inflate(inflater, container, false)
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
                binding?.vinTextView?.text = it.vin
                binding?.makeModelTextView?.text = it.make_and_model
                binding?.colorTextView?.text = it.color
                binding?.carTypeTextView?.text = it.car_type

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

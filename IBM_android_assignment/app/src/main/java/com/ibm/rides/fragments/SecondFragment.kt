package com.ibm.rides.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.viewmodel.VehicleViewModel
import com.ibm.rides.databinding.FragmentSecondBinding

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
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            endFragment = this@SecondFragment
        }

        // Observe the selected vehicle and update UI elements
        sharedViewModel.selectedVehicle.observe(viewLifecycleOwner) { vehicle ->
            Log.e("######567",vehicle.toString())
            vehicle?.let {
                binding?.vinTextView?.text = it.vin
                binding?.makeModelTextView?.text = it.make_and_model
                binding?.colorTextView?.text = it.color
                binding?.carTypeTextView?.text = it.car_type
            }
        }

//        binding!!.cancelButton.setOnClickListener {
//            // Handle cancel button click (optional)
//        }
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
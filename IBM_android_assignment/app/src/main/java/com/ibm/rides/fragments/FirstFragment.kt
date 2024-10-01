package com.ibm.rides.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.VehicleViewModel
import com.ibm.rides.databinding.FragmentFirstBinding

/**
 * This is the first screen of the app.
 */
class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null

    private val sharedViewModel: VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFirstBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }


    fun actionRedirect(quantity: Int) {
        // Update the view model with the quantity
//        sharedViewModel.setQuantity(quantity)

        findNavController().navigate(R.id.action_startFragment_to_endFragment)
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
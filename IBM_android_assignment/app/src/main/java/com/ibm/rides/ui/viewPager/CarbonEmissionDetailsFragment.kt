package com.ibm.rides.ui.viewPager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.ibm.rides.R
import com.ibm.rides.databinding.FragmentCarbonEmmissionBinding
import com.ibm.rides.viewmodel.VehicleViewModel

class CarbonEmissionDetailsFragment : Fragment() {

    private var binding: FragmentCarbonEmmissionBinding? = null
    private val sharedViewModel: VehicleViewModel by activityViewModels()
    private lateinit var lineChart: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCarbonEmmissionBinding.inflate(inflater, container, false)
        lineChart = fragmentBinding.lineChart
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
                val carbonEmissions = sharedViewModel.getCarbonEmissionsForSelectedVehicle()
                binding?.carbonEmissionsTextView?.text = formatCarbonEmissions(carbonEmissions!!.toFloat())

                setupLineChart(carbonEmissions.toFloat())

                analyzeCarbonEmissions(carbonEmissions.toFloat())
            }
        }
    }

    private fun formatCarbonEmissions(carbonEmissions: Float?): String {
        return if (carbonEmissions != null) {
            getString(R.string.carbon_emissions_format, carbonEmissions)
        } else {
            getString(R.string.n_a)
        }
    }

    private fun setupLineChart(carbonEmissions: Float) {
        val entries = ArrayList<Entry>()
        val sampleData = listOf(carbonEmissions)

        sampleData.forEachIndexed { index, value ->
            entries.add(Entry(index.toFloat(), value))
        }

        val dataSet = LineDataSet(entries, getString(R.string.carbon_emission))
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        dataSet.setDrawValues(true)

        val lineData = LineData(dataSet)

        lineChart.data = lineData
        val description = com.github.mikephil.charting.components.Description()
        description.text = getString(R.string.carbon_emission)
        lineChart.description = description
        lineChart.invalidate()
    }

    private fun analyzeCarbonEmissions(carbonEmissions: Float?) {
        carbonEmissions?.let {
            val emissionLevel = when {
                it < 100 -> "low"
                else -> "high"
            }
            val analysisStatement = getString(R.string.carbon_emission_analysis, it, emissionLevel)
            binding?.emissionAnalysisTextView?.text = analysisStatement
            binding?.emissionAnalysisTextView?.text = getString(R.string.no_emissions_data_available)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

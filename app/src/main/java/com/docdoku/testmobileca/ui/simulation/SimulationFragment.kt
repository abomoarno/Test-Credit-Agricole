package com.docdoku.testmobileca.ui.simulation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.docdoku.testmobileca.R
import com.docdoku.testmobileca.databinding.EmptyFragmentBinding

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * Fragment created to satisfy the design of the application.
 */
class SimulationFragment: Fragment(R.layout.empty_fragment) {

    private lateinit var binding: EmptyFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = EmptyFragmentBinding.bind(view)

        binding.apply {
            tvEmpty.text = getString(R.string.empty_fragment_holder, "Simulation")
        }
    }

}
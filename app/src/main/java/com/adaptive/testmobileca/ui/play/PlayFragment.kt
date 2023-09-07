package com.adaptive.testmobileca.ui.play

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.adaptive.testmobileca.R
import com.adaptive.testmobileca.databinding.EmptyFragmentBinding


/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * Fragment created to satisfy the design of the application.
 */

class PlayFragment: Fragment(R.layout.empty_fragment) {

    private lateinit var binding: EmptyFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = EmptyFragmentBinding.bind(view)

        binding.apply {
            tvEmpty.text = getString(R.string.empty_fragment_holder, "Play")
        }
    }

}
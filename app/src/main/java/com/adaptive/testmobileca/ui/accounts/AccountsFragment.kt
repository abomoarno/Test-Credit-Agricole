package com.adaptive.testmobileca.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.adaptive.testmobileca.R
import com.adaptive.testmobileca.databinding.FragmentAccountsBinding
import com.adaptive.testmobileca.ui.MainViewModel
import com.adaptive.testmobileca.ui.details.AccountDetailsFragment
import com.adaptive.testmobileca.utils.ResultStatus
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Arno ABOMO on 09/06/2023
 */

@AndroidEntryPoint
class AccountsFragment: Fragment() {

    private lateinit var binding: FragmentAccountsBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // This try/catch block is use to avoid inflating the view again when the back button is pressed

        try {
            binding.rvBanks
        } catch (e: Exception) {
            binding = FragmentAccountsBinding.inflate(inflater, container, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.banks.observe(viewLifecycleOwner) { resultStatus ->
            when(resultStatus) {
                is ResultStatus.Success -> {

                    /*
                     * This "if" block is used to avoid inflating the view again when the back button is pressed.
                     * Otherwise, the adapter we will loose the RecyclerView state
                     */

                    if (binding.rvBanks.adapter != null){
                        return@observe
                    }

                    binding.loadingLayout.root.visibility = View.GONE
                    binding.rvBanks.adapter = BankAdapter(
                        items = resultStatus.data.sortedByDescending {
                            it.isCA
                        }.groupBy {
                            it.isCA
                        }.flatMap { entry ->
                            val title = getString(if (entry.key == 1) R.string.ca_title else R.string.others_title)
                            mutableListOf<DisplayItem>(DisplayItem.SectionItem(title)).apply {
                                addAll(entry.value.sortedBy { it.name }.map { bank -> DisplayItem.BankItem(bank) })
                            }
                        }
                    ){
                        viewModel.goToDetails(it)
                    }

                    binding.rvBanks.visibility = View.VISIBLE
                }

                is ResultStatus.Error -> {
                    binding.loadingLayout.root.visibility = View.GONE
                    binding.errorLayout.root.visibility = View.VISIBLE
                }

                else -> {
                    binding.errorLayout.root.visibility = View.GONE
                    binding.rvBanks.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }
            }
        }

        /*
         * This "if" block is used to avoid a new call to the server when the back button is pressed
         */

        if (viewModel.banks.value !is ResultStatus.Success) {
            viewModel.getBanks()
        }
    }

}
package com.docdoku.testmobileca.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.docdoku.testmobileca.R
import com.docdoku.testmobileca.databinding.FragmentAccountDetailsBinding
import com.docdoku.testmobileca.domain.BankAccount
import com.docdoku.testmobileca.domain.Operation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/**
 * Fragment to display the details of a each bank account.
 * This includes the account balance, the account name and the list of operations for this account.
 */

class AccountDetailsFragment: Fragment(R.layout.fragment_account_details) {

    private lateinit var binding: FragmentAccountDetailsBinding

    private var mBankAccount: BankAccount? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountDetailsBinding.bind(view)
        mBankAccount = arguments?.getParcelable(ARG_ACCOUNT)

        mBankAccount?.let { bankAccount ->

            binding.apply {
                tvAccountBalance.text = getString(R.string.balance_holder, bankAccount.balance.toString())
                tvAccountLabel.text = bankAccount.label
            }

            if (bankAccount.operations.isNotEmpty()) {

                binding.rvOperations.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )

                // Sort the operations by date then by title

                binding.rvOperations.adapter = OperationAdapter(
                    bankAccount.operations.sortedWith(compareBy<Operation> {
                        SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(Date(it.date.toLong()))
                    }.thenBy { it.title })
                )

                binding.rvOperations.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.rvOperations.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        } ?: run {
            throw Exception("Bank account can't be null")
        }
    }

    companion object {

        // This constant will be used to pass the bank account to the fragment as a Parcelable
        const val ARG_ACCOUNT = "account"
    }

}
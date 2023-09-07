package com.adaptive.testmobileca.ui.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adaptive.testmobileca.databinding.BankAccountItemViewBinding
import com.adaptive.testmobileca.domaine.BankAccount

/**
 * Created by Arno ABOMO on 09/06/2023
 */

class AccountAdapter(
    private val account: List<BankAccount>,
    private val onSelectBankAccount: (account: BankAccount) -> Unit
): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            BankAccountItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return account.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(account[position])
    }

    inner class AccountViewHolder(private val binding: BankAccountItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(account: BankAccount) {
            binding.txtBankAccountName.text = account.label
            binding.txtBankAccountBalance.text = binding.root.context.getString(
                com.adaptive.testmobileca.R.string.balance_holder,
                account.balance.toString()
            )

            binding.root.setOnClickListener {
                onSelectBankAccount(account)
            }
        }
    }

}
package com.docdoku.testmobileca.ui.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.docdoku.testmobileca.R
import com.docdoku.testmobileca.databinding.BankGroupItemViewBinding
import com.docdoku.testmobileca.databinding.BankItemViewBinding
import com.docdoku.testmobileca.domain.Bank
import com.docdoku.testmobileca.domain.BankAccount

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * Adapter for the list of banks.
 * This adapter is used to display the list of banks and their accounts.
 * It will also display the section in which the bank belongs to.
 */

class BankAdapter(
    private val items: List<DisplayItem>,
    private val onSelectBankAccount: (account: BankAccount) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> BankViewHolder(
                BankItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> SectionViewHolder(
                BankGroupItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is DisplayItem.BankItem -> 0
            is DisplayItem.SectionItem -> 1
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when(holder) {
                is BankViewHolder -> {
                    holder.bind((items[position] as DisplayItem.BankItem).bank)
                }

                is SectionViewHolder -> {
                    holder.bind((items[position] as DisplayItem.SectionItem).title)
                }
            }
    }

    /**
     * View holder for the list of banks
     *
     * @param binding
     */
    inner class BankViewHolder(private val binding: BankItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(bank: Bank) {

            binding.txtBankName.text = bank.name

            binding.txtBankBalance.text = binding.root.context.getString(
                com.docdoku.testmobileca.R.string.balance_holder,
                String.format("%.2f", bank.bankAccounts.sumOf { it.balance })
            )

            if (bank.bankAccounts.size > 1){
                binding.rvBankAccounts.addItemDecoration(DividerItemDecoration(binding.root.context, DividerItemDecoration.VERTICAL))
                binding.rvBankAccounts.addItemDecoration(DividerItemDecoration(binding.root.context, DividerItemDecoration.VERTICAL))
            }

            binding.rvBankAccounts.adapter = AccountAdapter(bank.bankAccounts) {
                onSelectBankAccount(it)
            }

            binding.root.setOnClickListener {
                if (binding.rvBankAccounts.visibility == RecyclerView.VISIBLE) {
                    binding.rvBankAccounts.visibility = RecyclerView.GONE
                    binding.txtBankBalance.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0)
                } else {
                    binding.rvBankAccounts.visibility = RecyclerView.VISIBLE
                    binding.txtBankBalance.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.upload, 0)
                }
            }
        }
    }

    /**
     * View holder for the the section title
     *
     * @param binding: BankGroupItemViewBinding
     */
    inner class SectionViewHolder(private val binding: BankGroupItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.txtBankGroupName.text = title
        }
    }

}

sealed class DisplayItem {
    data class BankItem(val bank: Bank): DisplayItem()
    data class SectionItem(val title: String): DisplayItem()
}
package com.adaptive.testmobileca.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adaptive.testmobileca.R
import com.adaptive.testmobileca.databinding.OperationItemViewBinding
import com.adaptive.testmobileca.domaine.Operation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/**
 * Adapter to display the list of operations for a bank account.
 *
 * @param operations the list of operations to display
 */

class OperationAdapter(
    private val operations: List<Operation>
): RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        return OperationViewHolder(
            OperationItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        holder.bind(operations[position])
    }

    /**
     * View holder for each operation item.
     */
    inner class OperationViewHolder(private val binding: OperationItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(operation: Operation) {

            binding.tvOperationLabel.text = operation.title

            binding.tvOperationBalance.text = binding.root.context.getString(R.string.balance_holder, operation.amount.toString())

            binding.tvOperationDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(
                Date(operation.date.toLong())
            )

        }
    }
}
package com.example.perfinmanagement.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perfinmanagement.R
import com.example.perfinmanagement.data.entities.Expense
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]
        holder.itemView.list_id_Expense.text = currentItem.id.toString()
        holder.itemView.list_value_expense.text = currentItem.value_expense.toString()
        holder.itemView.list_description_expense.text = currentItem.description_expense
        holder.itemView.list_categroy_expense.text = currentItem.category_expense
    }

    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }


}
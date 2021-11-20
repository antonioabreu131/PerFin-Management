package com.example.perfinmanagement.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perfinmanagement.R
import com.example.perfinmanagement.data.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlin.math.exp

class ListFragment : Fragment() {

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ExpenseViewModel
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        mExpenseViewModel.ReadAllData.observe(viewLifecycleOwner, Observer { expense -> adapter.setData(expense)

        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }
}
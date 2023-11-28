package com.example.vk_kotlin_task2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_kotlin_task2.adapters.CatAdapter


class CatListFragment : Fragment() {
    private lateinit var catRecyclerView: RecyclerView
    private lateinit var viewModel: CatListViewModel
    private lateinit var adapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catRecyclerView = view.findViewById(R.id.catRecyclerView)

        viewModel = ViewModelProvider(this)[CatListViewModel::class.java]
        adapter = CatAdapter(mutableListOf())

        catRecyclerView.layoutManager = LinearLayoutManager(context)
        catRecyclerView.adapter = adapter

        observeViewModel()
        viewModel.getCats()

        catRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreCats()

                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel.cats.observe(viewLifecycleOwner, Observer { cats ->
            adapter.updateCats(cats)
        })

//            viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
//            if (isLoading) {
//                TODO()
//            } else {
//                TODO()
//            }
//        })
    }
}
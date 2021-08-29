package com.zref.masakapa.listfood

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.text.HtmlCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zref.core.extension.dp
import com.zref.masakapa.BaseFragment
import com.zref.masakapa.databinding.FragmentListFoodBinding
import com.zref.masakapa.listfood.adapter.FoodAdapter
import com.zref.model.Meal
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFoodFragment : BaseFragment() {
    private val viewModel by viewModel<ListFoodViewModel>()
    private lateinit var binding: FragmentListFoodBinding
    private val listFood = arrayListOf<Meal>()

    private lateinit var adapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListFoodBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        layoutToolbar.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                recyclerFood.setPadding(
                    recyclerFood.paddingLeft,
                    (layoutToolbar.height + 8.dp).toInt(),
                    recyclerFood.paddingRight,
                    recyclerFood.paddingBottom
                )
                layoutToolbar.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        adapter = FoodAdapter(listFood)
        recyclerFood.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerFood.adapter = adapter

        inputSearch.setOnEditorActionListener { v, actionId, event ->
            viewModel.findMeals(inputSearch.text.toString())
            true
        }
    }

    private fun initObserver() {
        viewModel.listFood.observe(this, {
            listFood.clear()
            listFood.addAll(it!!)
            adapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(this, {
            binding.progressBar.isGone = !it
        })

        viewModel.errorMessage.observe(this, {
            val text = HtmlCompat.fromHtml(
                "<font color=\"#ff0000\">$it</font>",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            val snackbar = Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        })

    }
}
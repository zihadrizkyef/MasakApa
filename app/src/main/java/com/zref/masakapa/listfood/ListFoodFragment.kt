package com.zref.masakapa.listfood

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zref.core.Sort
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

    private lateinit var mealsAdapter: FoodAdapter
    private var searchParam = ""
    private var categoryParam = ""
    private var sortParam = Sort.ASCENDING

    private lateinit var categoryAdapter: ArrayAdapter<String>

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
        setupRecyclerPadding()

        categoryAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        mealsAdapter = FoodAdapter(listFood)
        recyclerFood.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerFood.adapter = mealsAdapter

        inputSearch.setOnEditorActionListener { _, _, _ ->
            searchParam = inputSearch.text.toString()
            viewModel.findMeals(searchParam, categoryParam, sortParam)
            true
        }

        buttonSort.setOnClickListener { showSortPopup() }
        buttonCategory.setOnClickListener { showCategoryPopup() }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        viewModel.listFood.observe(this, {
            listFood.clear()
            listFood.addAll(it!!)
            mealsAdapter.notifyDataSetChanged()
        })

        viewModel.categoryList.observe(this, {
            categoryAdapter.addAll(it)
        })

        viewModel.isLoading.observe(this, {
            binding.progressBar.isGone = !it
        })

        viewModel.errorMessage.observe(this, {
            val text = HtmlCompat.fromHtml(
                "<font color=\"#ff0000\">$it</font>",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            val snackBar = Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG)
            snackBar.view.setBackgroundColor(Color.RED)
            snackBar.show()
        })
    }

    private fun setupRecyclerPadding() = with(binding) {
        layoutToolbar.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                recyclerFood.setPadding(
                    recyclerFood.paddingLeft,
                    layoutToolbar.height,
                    recyclerFood.paddingRight,
                    recyclerFood.paddingBottom
                )
                layoutToolbar.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun showCategoryPopup() {
        AlertDialog.Builder(requireContext())
            .setAdapter(categoryAdapter) { _, position ->
                categoryParam = categoryAdapter.getItem(position)!!
                viewModel.findMeals(searchParam, categoryParam, sortParam)
            }
            .show()
    }

    private fun showSortPopup() {
        val popup = PopupMenu(requireContext(), binding.buttonSort)
        popup.menu.add("Ascending").setOnMenuItemClickListener {
            sortParam = Sort.ASCENDING
            viewModel.findMeals(searchParam, categoryParam, sortParam)
            true
        }
        popup.menu.add("Descending").setOnMenuItemClickListener {
            sortParam = Sort.DESCENDING
            viewModel.findMeals(searchParam, categoryParam, sortParam)
            true
        }
        popup.show()
    }
}
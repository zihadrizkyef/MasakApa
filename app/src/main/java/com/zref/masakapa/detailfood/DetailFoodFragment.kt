package com.zref.masakapa.detailfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zref.core.Extra
import com.zref.masakapa.BaseFragment
import com.zref.masakapa.databinding.FragmentDetailFoodBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Intent
import android.net.Uri
import androidx.core.view.isGone
import androidx.transition.ChangeBounds
import com.zref.model.Ingredient
import io.realm.RealmList
import kotlin.random.Random


class DetailFoodFragment : BaseFragment() {
    private val viewModel by viewModel<DetailFoodViewModel>()
    private lateinit var binding: FragmentDetailFoodBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idDetail = arguments?.getString(Extra.ID, null) ?: "0"
        viewModel.getMealDetail(idDetail.toInt())
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.mealDetail.observe(this, { meal ->
            with(binding) {
                Glide.with(this@DetailFoodFragment)
                    .load(meal.image)
                    .into(imageView)

                ratingBar.rating = meal.rating ?: 0F
                textName.text = meal.name
                textCategory.text = "Category : " + meal.category
                textCountry.text = meal.area + " Food"
                if (meal.tags.isNullOrBlank()) {
                    textTags.isGone = true
                } else {
                    textTags.text = "Tags : " + meal.tags!!.replace(",", ", ")
                }
                textIngredient.text = makeIngredientsText(meal.ingredients!!)
                textInstruction.text = meal.instructions

                buttonYoutube.setOnClickListener {
                    val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(meal.youtube))
                    startActivity(youtubeIntent)
                }
            }
        })

        viewModel.errorMessage.observe(this, {
            showErrorMessage(it, binding.root)
        })
    }

    private fun makeIngredientsText(ingredients: RealmList<Ingredient>): String {
        var string = ""
        ingredients.forEach {
            if (!it.name.isNullOrEmpty()) {
                string += "• ${it.name} : ${it.measure}\n"
            }
        }
        return string
    }
}
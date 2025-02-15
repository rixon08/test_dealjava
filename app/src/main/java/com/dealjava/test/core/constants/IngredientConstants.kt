package com.dealjava.test.core.constants

import com.dealjava.test.core.models.IngredientModel
import com.dealjava.test.R

class IngredientConstants {
    companion object {
        const val egg: String = "egg"
        const val cheese: String = "cheese"
        const val bread: String = "bread"
        const val tomato: String = "tomato"
        const val chicken: String = "chicken"

        val listIngredient: List<IngredientModel> = listOf(
            IngredientModel(egg, R.raw.egg_lottie),
            IngredientModel(cheese, R.raw.cheese_lottie),
            IngredientModel(bread, R.raw.bread_lottie),
            IngredientModel(tomato, R.raw.tomato_lottie),
            IngredientModel(chicken, R.raw.chicken_lottie),
        )
    }
}
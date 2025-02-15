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
            IngredientModel(egg, R.raw.egg_lottie, R.raw.stand_egg_lottie, R.raw.combine_to_right_egg_lottie, R.raw.combine_to_left_egg_lottie),
            IngredientModel(cheese, R.raw.cheese_lottie, R.raw.stand_cheese_lottie, R.raw.combine_to_right_cheese_lottie, R.raw.combine_to_left_cheese_lottie),
            IngredientModel(bread, R.raw.bread_lottie, R.raw.stand_bread_lottie, R.raw.combine_to_right_bread_lottie, R.raw.combine_to_left_bread_lottie),
            IngredientModel(tomato, R.raw.tomato_lottie, R.raw.stand_tomato_lottie, R.raw.combine_to_right_tomato_lottie, R.raw.combine_to_left_tomato_lottie),
            IngredientModel(chicken, R.raw.chicken_lottie, R.raw.stand_chicken_lottie, R.raw.combine_to_right_chicken_lottie, R.raw.combine_to_left_chicken_lottie),
        )
    }
}
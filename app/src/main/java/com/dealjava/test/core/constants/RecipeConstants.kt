package com.dealjava.test.core.constants

import com.dealjava.test.R
import com.dealjava.test.core.constants.IngredientConstants.Companion.bread
import com.dealjava.test.core.constants.IngredientConstants.Companion.cheese
import com.dealjava.test.core.constants.IngredientConstants.Companion.chicken
import com.dealjava.test.core.constants.IngredientConstants.Companion.egg
import com.dealjava.test.core.constants.IngredientConstants.Companion.tomato
import com.dealjava.test.core.models.IngredientModel
import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel

class RecipeConstants {
    companion object {
//        val recipes = mapOf(
//            Pair(IngredientConstants.egg, IngredientConstants.cheese) to "Omelet Keju",
//            Pair(IngredientConstants.egg, IngredientConstants.bread) to "Sandwich Telur",
//            Pair(IngredientConstants.bread, IngredientConstants.tomato) to "Pasta Keju Tomat",
//            Pair(IngredientConstants.bread, IngredientConstants.chicken) to "Chicken Sandwich",
//            Pair(IngredientConstants.cheese, IngredientConstants.bread) to "Grilled Cheese Sandwich"
//        )

        val recipes = mapOf(
            Pair(IngredientConstants.egg, IngredientConstants.cheese) to RecipeModel(IngredientConstants.listIngredient[0], IngredientConstants.listIngredient[1], "Omelet Keju", R.raw.omelete_cheese_lottie),
            Pair(IngredientConstants.egg, IngredientConstants.bread) to RecipeModel(IngredientConstants.listIngredient[0], IngredientConstants.listIngredient[2], "Sandwich Telur", R.raw.egg_burger_lottie),
            Pair(IngredientConstants.bread, IngredientConstants.tomato) to RecipeModel(IngredientConstants.listIngredient[2], IngredientConstants.listIngredient[3], "Burger", R.raw.burger_lottie),
            Pair(IngredientConstants.bread, IngredientConstants.chicken) to RecipeModel(IngredientConstants.listIngredient[2], IngredientConstants.listIngredient[4], "Chicken Burger", R.raw.chicken_burger_lottie),
            Pair(IngredientConstants.cheese, IngredientConstants.bread) to RecipeModel(IngredientConstants.listIngredient[1], IngredientConstants.listIngredient[2], "Cheese Burger", R.raw.sandwich_cheese_lottie)
        )
    }
}
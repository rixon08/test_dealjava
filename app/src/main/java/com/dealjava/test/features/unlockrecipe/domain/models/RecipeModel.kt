package com.dealjava.test.features.unlockrecipe.domain.models

import com.dealjava.test.core.models.IngredientModel

data class RecipeModel(
    val ingredientModel1: IngredientModel,
    val ingredientModel2: IngredientModel,
    val name: String,
    val lottieSrc: Int
)

package com.dealjava.test.features.unlockrecipe.domain.repositories

import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel

interface UnlockRecipeRepository {
    suspend fun getUnlockRecipe(): Map<Pair<String, String>, RecipeModel>
}
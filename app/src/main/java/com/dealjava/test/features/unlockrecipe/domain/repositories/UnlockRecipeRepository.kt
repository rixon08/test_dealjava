package com.dealjava.test.features.unlockrecipe.domain.repositories

interface UnlockRecipeRepository {
    suspend fun getUnlockRecipe(): Map<Pair<String, String>, String>
}
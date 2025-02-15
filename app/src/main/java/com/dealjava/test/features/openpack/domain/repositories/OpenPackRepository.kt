package com.dealjava.test.features.openpack.domain.repositories

interface OpenPackRepository {
    suspend fun saveRecipe(recipe: Pair<String, String,>)
}
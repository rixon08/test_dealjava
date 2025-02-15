package com.dealjava.test.core.datasources

import android.content.Context
import com.dealjava.test.core.constants.RecipeConstants
import com.dealjava.test.features.unlockrecipe.domain.models.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalDataStorage (context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyRecipe", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val recipeKey = "recipe_key"

    fun getUnlockRecipe(): Map<Pair<String, String>, RecipeModel> {
        val json = sharedPreferences.getString(recipeKey, null)
        val type = object : TypeToken<List<Pair<String, String>>>() {}.type
        val listRecipe: List<Pair<String, String>> = gson.fromJson(json, type) ?: emptyList()
        var mapResult: MutableMap<Pair<String, String>, RecipeModel> = mutableMapOf()
        for (recipe in listRecipe){
            if (RecipeConstants.recipes.containsKey(
                Pair(
                    recipe.first,
                    recipe.second
                )
            ) || RecipeConstants.recipes.containsKey(
                Pair(
                    recipe.second,
                    recipe.first
                )
            )){
                if (RecipeConstants.recipes.containsKey(
                        Pair(
                            recipe.first,
                            recipe.second
                        )
                    )){
                    mapResult[Pair(
                        recipe.first,
                        recipe.second
                    )] = RecipeConstants.recipes[Pair(
                        recipe.first,
                        recipe.second
                    )] as RecipeModel
                } else {
                    mapResult[Pair(
                        recipe.second,
                        recipe.first
                    )] = RecipeConstants.recipes[Pair(
                        recipe.second,
                        recipe.first
                    )] as RecipeModel
                }
            }
        }
        return mapResult
    }

    private fun getUnlockRecipeMapString(): Map<Pair<String, String>, String> {
        val json = sharedPreferences.getString(recipeKey, null)
        val type = object : TypeToken<List<Pair<String, String>>>() {}.type
        val listRecipe: List<Pair<String, String>> = gson.fromJson(json, type) ?: emptyList()
        var mapResult: MutableMap<Pair<String, String>, String> = mutableMapOf()
        for (recipe in listRecipe){
            if (RecipeConstants.recipes.containsKey(
                    Pair(
                        recipe.first,
                        recipe.second
                    )
                ) || RecipeConstants.recipes.containsKey(
                    Pair(
                        recipe.second,
                        recipe.first
                    )
                )){
                if (RecipeConstants.recipes.containsKey(
                        Pair(
                            recipe.first,
                            recipe.second
                        )
                    )){
                    mapResult[Pair(
                        recipe.first,
                        recipe.second
                    )] = (RecipeConstants.recipes[Pair(
                        recipe.first,
                        recipe.second
                    )] as RecipeModel).name
                } else {
                    mapResult[Pair(
                        recipe.second,
                        recipe.first
                    )] = (RecipeConstants.recipes[Pair(
                        recipe.second,
                        recipe.first
                    )] as RecipeModel).name
                }
            }
        }
        return mapResult
    }

    fun saveRecipe(recipe: Pair<String, String>) {
        val listUnlockRecipe = getUnlockRecipeMapString()
        val isExist = listUnlockRecipe.containsKey(
            Pair(
                recipe.first,
                recipe.second
            )
        ) || listUnlockRecipe.containsKey(
            Pair(
                recipe.second,
                recipe.first
            )
        )
        if (!isExist) {
            var list: List<Pair<String, String>> = listUnlockRecipe.keys.toList() + recipe
            val json = gson.toJson(list)
            sharedPreferences.edit().putString(recipeKey, json).apply()
        }
    }
}
package com.dealjava.test.core.constants

class RecipeConstants {
    companion object {
        val recipes = mapOf(
            Pair(IngredientConstants.egg, IngredientConstants.cheese) to "Omelet Keju",
            Pair(IngredientConstants.egg, IngredientConstants.bread) to "Sandwich Telur",
            Pair(IngredientConstants.cheese, IngredientConstants.tomato) to "Pasta Keju Tomat",
            Pair(IngredientConstants.bread, IngredientConstants.chicken) to "Chicken Sandwich",
            Pair(IngredientConstants.cheese, IngredientConstants.bread) to "Grilled Cheese Sandwich"
        )
    }
}
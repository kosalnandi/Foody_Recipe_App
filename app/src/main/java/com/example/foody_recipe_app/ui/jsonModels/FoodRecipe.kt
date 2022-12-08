package com.example.foody_recipe_app.ui.jsonModels


import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)
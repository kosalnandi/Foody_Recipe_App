package com.example.foody_recipe_app.ui.data

import com.example.foody_recipe_app.ui.data.network.FoodRecipesApi
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.movieModels.JsonMovie
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
   suspend fun getRecipes():Response<JsonMovie> {
        return foodRecipesApi.getRecipes()
    }

}
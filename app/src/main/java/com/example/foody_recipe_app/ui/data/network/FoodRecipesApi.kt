package com.example.foody_recipe_app.ui.data.network

import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.movieModels.JsonMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {
    @GET("en/API/Top250Movies/k_hc2sam6f")
    suspend fun getRecipes(): Response<JsonMovie>
}
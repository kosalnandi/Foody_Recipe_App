package com.example.foody_recipe_app.ui.movieModels


import com.google.gson.annotations.SerializedName

data class JsonMovie(
    @SerializedName("errorMessage")
    val errorMessage: String = " ",
    @SerializedName("items")
    val items: List<Item>
)
package com.example.foody_recipe_app.ui.database

import androidx.room.TypeConverter
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.movieModels.JsonMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: JsonMovie): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): JsonMovie {
        val listType = object : TypeToken<JsonMovie>() {}.type
        return gson.fromJson(data, listType)
    }

}
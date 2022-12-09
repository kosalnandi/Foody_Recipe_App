package com.example.foody_recipe_app.ui.uitl


open class Constants() {

    companion object {

        const val BASE_URL = "https://imdb-api.com/"
        const val API_KEY = "k_hc2sam6f"

        //API Queries Keys
        const val Query_NUMBER = "number"
        const val Query_API_KEY = "apiKey"
        const val Query_TYPE = "type"
        const val Query_DIET = "diet"
        const val Query_ADD_RECIPES_INFORMATIONS = "addRecipesInformation"
        const val Query_FILL_INGREDIENTS = "fillIngredients"

        //Room Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"

    }
}
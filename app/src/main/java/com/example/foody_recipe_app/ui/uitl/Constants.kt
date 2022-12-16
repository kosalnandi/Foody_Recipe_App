package com.example.foody_recipe_app.ui.uitl


open class Constants() {

    companion object {

        const val BASE_URL ="https://api.spoonacular.com"
        const val API_KEY = "9b017add950545e283ccf48d4e14dd76"

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

        //Bottom Sheet And Preference

        const val DEFAULT_RECIPE_NUMBER = "50"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"
    }
}
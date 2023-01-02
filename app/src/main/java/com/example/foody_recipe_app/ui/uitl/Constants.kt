package com.example.foody_recipe_app.ui.uitl


open class Constants() {

    companion object {

        const val BASE_URL ="https://api.spoonacular.com"
        const val API_KEY = "1e74da44e9924eb785e600a352356b1d"

        //API Queries Keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //Room Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"

        //Bottom Sheet And Preference
        const val DEFAULT_RECIPE_NUMBER = "50"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"

        const val PREFERENCES_NAME = "food recipe preferences"
        const val PREFERENCES_MEAL_TYPE = "meal type"
        const val PREFERENCE_MEAL_TYPE_ID = "meal type id"
        const val PREFERENCES_DIET_TYPE = "diet type"
        const val PREFERENCES_DIET_TYPE_ID = "diet type id"

    }
}
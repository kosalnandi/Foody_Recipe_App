package com.example.foody_recipe_app.ui.viewModels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.example.foody_recipe_app.ui.MyApplication
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.API_KEY
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.DEFAULT_RECIPE_NUMBER
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.Query_ADD_RECIPES_INFORMATIONS
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.Query_API_KEY
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.Query_DIET
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.Query_FILL_INGREDIENTS
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.Query_NUMBER
import com.example.foody_recipe_app.ui.uitl.Constants.Companion.Query_TYPE


class RecipesViewModels @ViewModelInject constructor(application: Application): AndroidViewModel(application) {

      fun applyQueries():HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[Query_NUMBER] = DEFAULT_RECIPE_NUMBER
        queries[Query_API_KEY] = API_KEY
        queries[Query_TYPE] = DEFAULT_MEAL_TYPE
        queries[Query_DIET] = DEFAULT_DIET_TYPE
        queries[Query_ADD_RECIPES_INFORMATIONS] = "true"
        queries[Query_FILL_INGREDIENTS] = "true"

        return queries
    }
}
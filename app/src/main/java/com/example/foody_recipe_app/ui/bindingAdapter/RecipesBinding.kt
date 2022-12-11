package com.example.foody_recipe_app.ui.bindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foody_recipe_app.ui.database.RecipeEntity
import com.example.foody_recipe_app.ui.jsonModels.FoodRecipe
import com.example.foody_recipe_app.ui.uitl.NetworkResult

class RecipesBinding {

    companion object  {

       @BindingAdapter("readApiResponse","readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisible(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipeEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            }
            else if (apiResponse is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            }
            else if (apiResponse is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }


        @BindingAdapter("readApiResponse2","readDatabase2")
        @JvmStatic
        fun errorTextViewVisible(
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipeEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            }
            else if (apiResponse is NetworkResult.Success) {
                textView.text = View.INVISIBLE.toString()
            }
            else if (apiResponse is NetworkResult.Loading) {
                textView.text = View.INVISIBLE.toString()
            }
        }
    }
}
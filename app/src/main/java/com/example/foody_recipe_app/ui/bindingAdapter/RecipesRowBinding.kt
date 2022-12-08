package com.example.foody_recipe_app.ui.bindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foody_recipe_app.R

class RecipesRowBinding {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("setNumberOfMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView: TextView, minutes: Int) {
            textView.text = minutes.toString()
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) { //little doubt
            if(vegan) {
                when(view) {
                    is TextView -> {
                        view.setTextColor(ContextCompat.getColor(
                            view.context,
                            R.color.lightGreen
                            )
                        )
                    }
                    is ImageView -> {
                        view.setColorFilter(ContextCompat.getColor(
                            view.context,
                            R.color.lightGreen
                            )
                        )
                    }
                }
            }
        }
    }
}
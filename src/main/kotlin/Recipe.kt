package org.example

import com.google.gson.annotations.SerializedName

class Recipe(
    @SerializedName("strMeal") val name: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strArea") val area: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strMealThumb") val thumbnail: String,
    @SerializedName("strYoutube") val video: String,
) {
    override fun toString(): String = "Recipe($name)"
}
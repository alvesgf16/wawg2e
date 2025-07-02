package org.example

data class Recipes(val meals: ArrayList<TheMealDBApiRecipe>) {
    override fun toString(): String = meals[0].toString()
}
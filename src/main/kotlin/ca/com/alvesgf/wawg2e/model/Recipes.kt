package ca.com.alvesgf.wawg2e.model

data class Recipes(val meals: ArrayList<TheMealDBApiRecipe>) {
    override fun toString(): String = meals[0].toString()
}
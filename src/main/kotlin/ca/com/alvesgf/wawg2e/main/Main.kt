package ca.com.alvesgf.wawg2e.main

import ca.com.alvesgf.wawg2e.model.Recipe
import ca.com.alvesgf.wawg2e.model.TheMealDBApiRecipe
import ca.com.alvesgf.wawg2e.services.ApiConsumption
import java.net.URLEncoder
import java.util.Scanner


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val read = Scanner(System.`in`)
    print("Enter a recipe to search: ")
    val recipeName = URLEncoder.encode(read.nextLine(), "UTF-8")

    val apiSearch = ApiConsumption()
    var apiRecipe: TheMealDBApiRecipe? = null

    val result = runCatching {
        apiRecipe = apiSearch.searchRecipe(recipeName)
    }

    result.onFailure { println("Recipe does not exist. Try another search.") }

    result.onSuccess {
        val recipe = Recipe(
            apiRecipe!!.strMeal,
            apiRecipe.strCategory,
            apiRecipe.strArea,
            apiRecipe.strInstructions,
            apiRecipe.strMealThumb,
            apiRecipe.strYoutube
        )

        println("Do you want to enter a custom description? Y/N")
        val option = read.nextLine()
        if (option.equals("y", true)) {
            print("Enter custom description for the recipe: ")
            val customDescription = read.nextLine()
            recipe.description = customDescription
        } else {
            recipe.description = recipe.name
        }

        println(recipe)
    }
}
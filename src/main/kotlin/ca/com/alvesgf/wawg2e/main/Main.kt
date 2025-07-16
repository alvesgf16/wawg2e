package ca.com.alvesgf.wawg2e.main

import ca.com.alvesgf.wawg2e.model.Recipe
import ca.com.alvesgf.wawg2e.model.TheMealDBApiRecipe
import ca.com.alvesgf.wawg2e.model.User
import ca.com.alvesgf.wawg2e.services.ApiConsumption
import ca.com.alvesgf.wawg2e.utils.turnIntoAge
import java.net.URLEncoder
import java.util.*


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val read = Scanner(System.`in`)

    val user = User.createUser(read)
    println("Registration completed successfully. Welcome, ${user.name}")
    println("User age: " + user.birthDate?.turnIntoAge())

    do {
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
                apiRecipe.strYoutube,
                apiRecipe.listIngredients
            )

            print("Do you want to enter a custom description? (Y/N) ")
            val option = read.nextLine()
            if (option.equals("y", true)) {
                print("Enter custom description for the recipe: ")
                val customDescription = read.nextLine()
                recipe.description = customDescription
            } else {
                recipe.description = recipe.name
            }

            user.searchedRecipes.add(recipe)
            println("Recipe added to the list: ${recipe.name}")
        }

        print("Do you want to search another recipe? (Y/N) ")
        val response = read.nextLine()
    } while (response.equals("y", true))

    println("Searched recipes:")
    println(user.searchedRecipes)

    println("\nRecipes ordered by name:")
    user.searchedRecipes.sortedBy { it.name }.forEach { println("- ${it.name}") }

    val filteredRecipes = user.searchedRecipes.filter { it.name.contains("eggs", true) }
    println("\nFiltered recipes:")
    println(filteredRecipes)

    print("Do you want to delete a game from the original list? (Y/N) ")
    val option = read.nextLine()

    if (option.equals("y", true)) {
        println(user.searchedRecipes)
        print("\nInform the position of the game you want to delete: ")
        val position = read.nextInt()
        user.searchedRecipes.removeAt(position)
    }

    println("\nUpdated list:")
    println(user.searchedRecipes)

    println("Search completed successfully")
}
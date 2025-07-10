package ca.com.alvesgf.wawg2e.main

import com.google.gson.Gson
import ca.com.alvesgf.wawg2e.model.Recipe
import ca.com.alvesgf.wawg2e.model.Recipes
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val read = Scanner(System.`in`)
    print("Enter a recipe to search: ")
    val queryParameter = URLEncoder.encode(read.nextLine(), "UTF-8")

    val uriString = "https://www.themealdb.com/api/json/v1/1/search.php?s=$queryParameter"

    val client: HttpClient = HttpClient.newHttpClient()
    val request: HttpRequest = HttpRequest.newBuilder()
        .uri(URI.create(uriString))
        .build()
    val response = client
        .send<String?>(request, BodyHandlers.ofString())
    val json = response.body()

    val gson = Gson()
    val apiRecipe = gson.fromJson(json, Recipes::class.java)

    var recipe: Recipe? = null

    val result = runCatching {
        recipe = Recipe(
            apiRecipe.meals[0].strMeal,
            apiRecipe.meals[0].strCategory,
            apiRecipe.meals[0].strArea,
            apiRecipe.meals[0].strInstructions,
            apiRecipe.meals[0].strMealThumb,
            apiRecipe.meals[0].strYoutube
        )
    }

    result.onFailure { println("Recipe does not exist. Try another search.") }

    result.onSuccess {
        println("Do you want to enter a custom description? Y/N")
        val option = read.nextLine()
        if (option.equals("y", true)) {
            print("Enter custom description for the recipe: ")
            val customDescription = read.nextLine()
            recipe?.description = customDescription
        } else {
            recipe?.description = recipe.name
        }

        println(recipe)
    }
}
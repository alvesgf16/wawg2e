package org.example

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val client: HttpClient = HttpClient.newHttpClient()
    val request: HttpRequest = HttpRequest.newBuilder()
        .uri(URI.create("https://www.themealdb.com/api/json/v1/1/search.php?s=Eggs%20Benedict"))
        .build()
    val response = client
        .send<String?>(request, BodyHandlers.ofString())
    val json = response.body()

    val gson = Gson()
    val apiEggsBenedict = gson.fromJson(json, Recipes::class.java)
    val eggsBenedict = Recipe(
        apiEggsBenedict.meals[0].strMeal,
        apiEggsBenedict.meals[0].strCategory,
        apiEggsBenedict.meals[0].strArea,
        apiEggsBenedict.meals[0].strInstructions,
        apiEggsBenedict.meals[0].strMealThumb,
        apiEggsBenedict.meals[0].strYoutube
    )

    print(eggsBenedict)
}
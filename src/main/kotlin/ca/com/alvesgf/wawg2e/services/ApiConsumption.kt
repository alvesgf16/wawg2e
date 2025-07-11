package ca.com.alvesgf.wawg2e.services

import ca.com.alvesgf.wawg2e.model.Recipes
import ca.com.alvesgf.wawg2e.model.TheMealDBApiRecipe
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ApiConsumption {
    fun searchRecipe(recipeName: String): TheMealDBApiRecipe {
        val uriString = "https://www.themealdb.com/api/json/v1/1/search.php?s=$recipeName"

        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(uriString))
            .build()
        val response = client
            .send<String?>(request, BodyHandlers.ofString())
        val json = response.body()

        val gson = Gson()
        val apiRecipe = gson.fromJson(json, Recipes::class.java)

        return apiRecipe.meals[0]
    }
}
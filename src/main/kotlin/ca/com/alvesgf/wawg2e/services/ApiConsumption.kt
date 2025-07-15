package ca.com.alvesgf.wawg2e.services

import ca.com.alvesgf.wawg2e.model.Recipes
import ca.com.alvesgf.wawg2e.model.TheMealDBApiRecipe
import ca.com.alvesgf.wawg2e.model.User
import ca.com.alvesgf.wawg2e.model.UserInfo
import ca.com.alvesgf.wawg2e.utils.createUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ApiConsumption {
    fun searchRecipe(recipeName: String): TheMealDBApiRecipe {
        val uriString = "https://www.themealdb.com/api/json/v1/1/search.php?s=$recipeName"

        val json = consumeData(uriString)

        val gson = Gson()
        val apiRecipe = gson.fromJson(json, Recipes::class.java)

        return apiRecipe.meals[0]
    }

    fun searchUsers(): List<User> {
        val uriString =
            "https://raw.githubusercontent.com/alvesgf16/wawg2e/refs/heads/main/src/main/resources/users.json"

        val json = consumeData(uriString)

        val gson = Gson()
        val userType = object : TypeToken<List<UserInfo>>() {}.type
        val userData: List<UserInfo> = gson.fromJson(json, userType)

        return userData.map(UserInfo::createUser)
    }

    private fun consumeData(uriString: String): String? {
        val client: HttpClient = HttpClient.newHttpClient()
        val request: HttpRequest = HttpRequest.newBuilder().uri(URI.create(uriString)).build()
        val response = client.send<String?>(request, BodyHandlers.ofString())
        return response.body()
    }
}
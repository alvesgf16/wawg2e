package ca.com.alvesgf.wawg2e.model

import ca.com.alvesgf.wawg2e.utils.formatWithTwoDecimalPlaces

data class Recipe(
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val video: String,
    val ingredients: List<Ingredient>,
) : Recommendable {
    var description: String? = null

    private val scores = mutableListOf<Int>()

    override val average: Double
        get() = scores.average().formatWithTwoDecimalPlaces()

    override fun recommend(score: Int) {
        if (score in 1..10) {
            scores.add(score)
        } else {
            println("Invalid score. Please enter a score between 1 and 10.")
        }
    }

    override fun toString(): String {
        val ingredientsString = ingredients.joinToString(separator = "\n            ") { "- ${it.name}: ${it.measure}" }
        val instructionsString = instructions.replace("\n", "\n            ")

        return """
            $name ($average)
            $description
            $category - $area
            
            Ingredients:
            $ingredientsString
            
            Instructions:
            $instructionsString
            
            Video: $video
        """.trimIndent()
    }
}
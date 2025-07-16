package ca.com.alvesgf.wawg2e.model

data class Recipe(
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val video: String,
    val ingredients: List<Ingredient>,
) {
    var description: String? = null

    override fun toString(): String {
        val ingredientsString = ingredients.joinToString(separator = "\n            ") { "- ${it.name}: ${it.measure}" }
        val instructionsString = instructions.replace("\n", "\n            ")

        return """
            $name
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
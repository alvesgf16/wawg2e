package ca.com.alvesgf.wawg2e.model

data class Recipe(
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val video: String,
) {
    var description: String? = null
    override fun toString(): String = "Recipe($name)"
}
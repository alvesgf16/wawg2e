package org.example

data class Recipe(
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val video: String,
) {
    override fun toString(): String = "Recipe($name)"
}
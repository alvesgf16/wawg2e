package ca.com.alvesgf.wawg2e.model

class Ingredient(val name: String, val measure: String) {
    override fun toString(): String = "Ingredient($measure of $name)"
}
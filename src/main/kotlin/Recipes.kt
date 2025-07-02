package org.example

class Recipes(val meals: ArrayList<Recipe>) {
    override fun toString(): String = meals[0].toString()
}
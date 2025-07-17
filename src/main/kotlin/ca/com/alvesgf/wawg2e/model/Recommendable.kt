package ca.com.alvesgf.wawg2e.model

interface Recommendable {
    val average: Double

    fun recommend(score: Int)
}
package com.example.healthlylife.data

data class RecipeData(
    val name: String,
    val ingredients: List<String>,
    val instructions: String,
    val imageList: List<Int>,
    val calories: Int,
    val proteins: Int,
    val carbs: Int,
    val fat: Int
)
package com.example.purchasecalculator.model

data class Product(
    val id: Int,
    val store: String?,
    val name: String,
    val value: Double,
    val quantity: Double,
    val type: String?
)
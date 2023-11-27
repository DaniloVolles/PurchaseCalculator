package com.example.purchasecalculator.model

data class Product (
    val store: String,
    val name: String,
    val value: Double,
    val quantity: Double,
    val type: Int
)
package com.example.purchasecalculator.util

data class PriceCalculator(var price: Double, var amount: Double) {

    private var pricePerUnit = 0.0

    init {
        pricePerUnit = (price/amount)
    }

    val pricePerUnitString = pricePerUnit.toString()

}
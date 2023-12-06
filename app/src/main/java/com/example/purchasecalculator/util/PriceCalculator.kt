package com.example.purchasecalculator.util

import java.lang.NumberFormatException

data class PriceCalculator(var price: Double, var amount: Double) {

    companion object {
        fun calculatePricePerUnit(price: String?, amount: String?): String {

            // Check if both values are provided
            if (price.isNullOrBlank() || amount.isNullOrBlank()) {
                return "Error: Both values must be provided."
            }

            return try {
                // Convert strings to doubles after trimming whitespace
                val priceDouble = price.trim().toDouble()
                val amountDouble = amount.trim().toDouble()

                // Check for division by zero
                if (amountDouble == 0.0) {
                    "Error: Cannot divide by zero."
                } else {
                    // Perform the calculation and format the result with two decimal places
                    val result = priceDouble / amountDouble
                    String.format("%.2f", result)
                }
            } catch (e: NumberFormatException) {
                "Error: Make sure to provide valid numeric values."
            }
        }
    }

}
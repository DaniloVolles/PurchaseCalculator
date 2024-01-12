package com.example.purchasecalculator.constants

class Constants private constructor() {
    object DATA_BASE {
        const val ID = "product_id"
        const val TABLE_NAME = "products"

        object COLUMNS {

            const val ID = "id"

            const val STORE = "store"
            const val NAME = "name"
            const val VALUE = "value"
            const val QUANTITY = "quantity"
            const val TYPE = "type"
        }
    }

    object PRODUCT {
        object TYPE {
            const val LITERS = "liters"
            const val METERS = "meters"
            const val UNITIES = "unities"
            const val GRAMS = "grams"
        }
    }
}

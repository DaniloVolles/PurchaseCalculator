package com.example.purchasecalculator.model

import com.example.purchasecalculator.constants.Constants

data class Product(
    val id: Int,
    val store: String?,
    val name: String,
    val value: Double,
    val quantity: Double,
    val type: String?
)

object ProductMock {
    val productList = listOf(
        Product(108, "Assaí", "Coca-Cola", 12.99, 3.0, Constants.PRODUCT.TYPE.LITERS),
        Product(109, "Extra", "Pepsi", 6.99, 2.0, Constants.PRODUCT.TYPE.LITERS),
        Product(110, "Adega", "Guaraná", 4.49, 1.5, Constants.PRODUCT.TYPE.LITERS),
        Product(111, "Extra", "Arroz Tio Joao", 25.99, 500.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(112, "Supermercado X", "Feijão", 8.99, 500.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(113, "Mercado Y", "Leite", 3.49, 1.0, Constants.PRODUCT.TYPE.LITERS),
        Product(114, "Hipermercado Z", "Ovos", 5.99, 12.0, Constants.PRODUCT.TYPE.UNITIES),
        Product(115, "Mercadinho A", "Macarrão", 2.29, 500.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(117, "Mini Mercado C", "Chocolate", 7.79, 200.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(118, "Empório D", "Vinho", 29.99, 0.75, Constants.PRODUCT.TYPE.LITERS),
        Product(119, "Super Shop E", "Sabonete", 1.99, 90.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(120, "Hortifruti F", "Banana", 3.49, 1000.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(121, "Supermercado G", "Queijo", 15.99, 300.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(122, "Mercado H", "Detergente", 2.99, 0.5, Constants.PRODUCT.TYPE.LITERS),
        Product(123, "Mini Mercado I", "Cebola", 2.49, 1000.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(124, "Hipermercado J", "Margarina", 4.29, 250.0, Constants.PRODUCT.TYPE.GRAMS),
        Product(125, "Empório K", "Cerveja", 6.49, 0.35, Constants.PRODUCT.TYPE.LITERS),
        Product(126, "Super Center L", "Shampoo", 8.99, 0.4, Constants.PRODUCT.TYPE.LITERS),
        Product(127, "Mini Mercado M", "Pão", 2.79, 500.0, Constants.PRODUCT.TYPE.GRAMS)
    )
}
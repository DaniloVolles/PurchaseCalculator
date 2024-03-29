package com.example.purchasecalculator.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.purchasecalculator.constants.Constants
import com.example.purchasecalculator.model.Product
import kotlin.Exception

class ProductRepository private constructor(context: Context) {

    private val productDataBase: ProductDataBase = ProductDataBase(context)

    val TABLE_NAME = Constants.DATA_BASE.TABLE_NAME

    val ID = Constants.DATA_BASE.COLUMNS.ID
    val NAME = Constants.DATA_BASE.COLUMNS.NAME
    val STORE = Constants.DATA_BASE.COLUMNS.STORE
    val VALUE = Constants.DATA_BASE.COLUMNS.VALUE
    val QUANTITY = Constants.DATA_BASE.COLUMNS.QUANTITY
    val TYPE = Constants.DATA_BASE.COLUMNS.TYPE

    companion object {
        private lateinit var repository: ProductRepository
        fun getInstance(context: Context): ProductRepository {
            if (!::repository.isInitialized) {
                repository = ProductRepository(context)
            }
            return repository
        }
    }

    fun insert(product: Product): Boolean {
        return try {
            val db = productDataBase.writableDatabase

            val values = ContentValues()
            values.put(NAME, product.name)
            values.put(STORE, product.store)
            values.put(VALUE, product.value)
            values.put(QUANTITY, product.quantity)
            values.put(TYPE, product.type)

            db.insert(TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }
    }

    @SuppressLint("Range")
    fun get(id: Int): Product? {
        var product: Product? = null
        return try {
            val db = productDataBase.readableDatabase

            val projection = arrayOf(NAME, STORE, VALUE, QUANTITY, TYPE)

            val selection = ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                TABLE_NAME, projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {

                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndex(NAME))
                val store = cursor.getString(cursor.getColumnIndex(STORE))
                val value = cursor.getDouble(cursor.getColumnIndex(VALUE))
                val quantity = cursor.getDouble(cursor.getColumnIndex(QUANTITY))
                val type = cursor.getString(cursor.getColumnIndex(TYPE))

                product = Product(id, store, name, value, quantity, type)
            }
            cursor?.close()
            product
        } catch (e: Exception) {
            product
        }
    }

    @SuppressLint("Range")
    fun getAll(): List<Product> {
        val list = mutableListOf<Product>()
        try {
            val db = productDataBase.readableDatabase

            val projection = arrayOf(ID, NAME, STORE, VALUE, QUANTITY, TYPE)

            val cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(ID))
                    val name = cursor.getString(cursor.getColumnIndex(NAME))
                    val store = cursor.getString(cursor.getColumnIndex(STORE))
                    val value = cursor.getDouble(cursor.getColumnIndex(VALUE))
                    val quantity = cursor.getDouble(cursor.getColumnIndex(QUANTITY))
                    val type = cursor.getString(cursor.getColumnIndex(TYPE))

                    list.add(Product(id, store, name, value, quantity, type))

                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun update(product: Product): Boolean {
        return try {

            val db = productDataBase.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(NAME, product.name)
            contentValues.put(STORE, product.store)
            contentValues.put(VALUE, product.value)
            contentValues.put(QUANTITY, product.quantity)
            contentValues.put(TYPE, product.type)

            val selection = ID + " = ?"
            val args = arrayOf(product.id.toString())

            db.update(TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception){
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = productDataBase.writableDatabase
            val selection = "$ID = ?"
            val args = arrayOf(id.toString())

            db.delete(TABLE_NAME, selection, args)
            true
        } catch (e: Exception){
            false
        }
    }

    fun deleteAll(): Boolean {
        return try {
            val db = productDataBase.writableDatabase
            db.delete(TABLE_NAME, null, null)
            true
        } catch (e: Exception){
            false
        }
    }
}


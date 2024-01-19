package com.example.purchasecalculator.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.purchasecalculator.constants.Constants
import java.util.jar.Attributes.Name

class ProductDataBase (context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {

        private const val NAME = "product_db"
        private const val VERSION = 1

        private const val CREATE_TABLE_PRODUCT =
            "CREATE TABLE " + Constants.DATA_BASE.TABLE_NAME + " (" +
                Constants.DATA_BASE.COLUMNS.ID + " integer PRIMARY KEY AUTOINCREMENT," +
                Constants.DATA_BASE.COLUMNS.STORE + " text," +
                Constants.DATA_BASE.COLUMNS.NAME + " text," +
                Constants.DATA_BASE.COLUMNS.TYPE + " text," +
                Constants.DATA_BASE.COLUMNS.VALUE + " double," +
                Constants.DATA_BASE.COLUMNS.QUANTITY + " double" +
                ");"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_PRODUCT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
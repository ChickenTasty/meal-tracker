package com.example.mealtracker.ui.theme.functions

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class MealDatabase(context: Context) : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {
    companion object {
        private const val databaseName = "MealTracker.db"
        private const val databaseVersion = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Creating database table
        val createTableSQL = "create table meals (foodID INTEGER PRIMARY KEY AUTOINCREMENT, foodName TEXT, calories INTEGER)"
        db.execSQL(createTableSQL)
    }
}
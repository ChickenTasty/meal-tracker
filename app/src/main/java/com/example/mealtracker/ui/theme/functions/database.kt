package com.example.mealtracker.ui.theme.functions

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Random

import com.google.firebase.database.FirebaseDatabase

// Initialising the firebase
val MealDatabase = FirebaseDatabase.getInstance()
val mealsRef = MealDatabase.getReference("meals")

// Adding meal function
fun addMeal(mealID: String, mealData: Map<String, Any>) {
    mealsRef.child(mealID).setValue(mealData)
}

fun main() {
    // Example: Add a breakfast burrito meal
    val breakfastBurrito = mapOf(
        "mealName" to "Breakfast Burrito",
        "mealIngredients" to listOf("Eggs", "Tortilla", "Cheese", "Sausage", "Tomatoes", "Onions"),
        "calories" to 450
    )

    addMeal("mealID1", breakfastBurrito)
}



/*
data class TrackerModel(
    var mealid: Int = getID(),
    var mealName: String = "",
    var ingredients: String = "",
    var calories: Int,
    var carbonfootprint: Int
) {
    companion object {
        fun getID(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}

class MealDatabase(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {
    companion object {
        private const val DATABASENAME = "mealtracker.db"
        private const val DATABASEVERSION = 1
        private const val MEALTABLE = "mealtable"
        private const val MEALID = "id"
        private const val MEALNAME = "name"
        private const val INGREDIENTS = "ingredients"
        private const val CALORIES = "calories"
        private const val CARBONFOOTPRINT = "carbonfootprint"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTblmeal = ("CREATE TABLE $MEALTABLE ("
                + "$MEALID INTEGER PRIMARY KEY,"
                + "$MEALNAME TEXT,"
                + "$INGREDIENTS TEXT,"
                + "$CALORIES TEXT,"
                + "$CARBONFOOTPRINT TEXT)")
        db.execSQL(createTblmeal)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $MEALTABLE")
        if (db != null) {
            onCreate(db)
        }
    }

    fun insertMeal(std: TrackerModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MEALID, std.mealid)
        contentValues.put(MEALNAME, std.mealName)
        contentValues.put(INGREDIENTS, std.ingredients)
        contentValues.put(CALORIES, std.calories)
        contentValues.put(CARBONFOOTPRINT, std.carbonfootprint)

        val success = db.insert(MEALTABLE, null, contentValues)
        db.close()
        return success
    }

    fun getAllMeals(): ArrayList<TrackerModel> {
        val stdList: ArrayList<TrackerModel> = ArrayList()
        val selectQuery = "SELECT * FROM $MEALTABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var calories: Int
        var ingredients: String
        var carbonfootprint: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.run { getColumnIndex(MEALID) })
                name = cursor.getString(cursor.run { getColumnIndex(MEALNAME) })
                ingredients = cursor.getString(cursor.run { getColumnIndex(INGREDIENTS) })
                calories = cursor.getInt(cursor.run { getColumnIndex(CALORIES) })
                carbonfootprint = cursor.getInt(cursor.run { getColumnIndex(CARBONFOOTPRINT) })

                val std = TrackerModel(mealid = id, mealName = name, calories = calories, ingredients = ingredients, carbonfootprint = carbonfootprint)
                stdList.add(std)

            } while (cursor.moveToNext())

        }

        return stdList
    }
}
*/
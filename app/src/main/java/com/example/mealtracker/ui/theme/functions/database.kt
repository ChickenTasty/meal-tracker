package com.example.mealtracker.ui.theme.functions

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.material3.contentColorFor
import java.util.Random



data class trackerModel( //giving id's to the items in the database
    var id: Int = getID(),
    var mealName: String = "",
    var ingredients: String = "",
    var calories: Int,
    var carbonfootprint: Int
){
    companion object{
        fun getID(): Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}
class MealDatabase(context: Context) :
    SQLiteOpenHelper(context, databaseName, null, databaseVersion) {
    companion object { //creating values for the columns in the database with all necessary information
        private const val databaseName = "MealTracker.db"
        private const val databaseVersion = 1
        private const val MEALTABLE = "Meal_Table"
        private const val ID = "id"
        private const val MEALNAME = "Name"
        private const val INGREDIENTS = "Ingredients"
        private const val CALORIES = "Calories"
        private const val CARBONFOOTPRINT = "CarbonFootPrint"

    }

    override fun onCreate(db: SQLiteDatabase) { //creation of the database
        //Creating database table
        val createTblmeal = ("Create Table" + MEALTABLE
                + "(" + ID + "Primary Key"
                + MEALNAME + "Text,"
                + INGREDIENTS + "Text,"
                + CALORIES + "Text,"
                + CARBONFOOTPRINT + "Text," + ")")
        db?.execSQL(createTblmeal)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){ //upgrading the datatbase version when new items are put in
        db!!.execSQL("DROP TABLE ${MealDatabase}")
        onCreate(db)
    }

    fun insertMeal(std: trackerModel): Long { //used to insert the meas into the database
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(MEALNAME, std.mealName)
        contentValues.put(INGREDIENTS, std.ingredients)
        contentValues.put(CALORIES, std.calories)
        contentValues.put(CARBONFOOTPRINT, std.carbonfootprint)

        val success = db.insert(MEALTABLE, null, contentValues)
        db.close()
        return success
    }

    fun getMeal() : ArrayList<trackerModel> {
        val stdList: ArrayList<trackerModel> = ArrayList()
        val selectQuery = "SELECT * FROM $MEALTABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id : Int
        var name : String


        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(MEALNAME))

                val std = trackerModel(id = id, mealName = name)
                stdList.add(std)

            } while (cursor.moveToNext())

        }

        return stdList
    }

}
/*
data class Meal(val id: Int, val name: String, val calories: Int, val ingredients: List<String>)

sealed class MealResult<out T> {
    data class MealAdded(val meal: Meal) : MealResult<Meal>()
    data class MealError(val message: String) : MealResult<Nothing>()
}

data class MealAdded(val meal: Meal) : MealResult<Meal>()
data class MealUpdated(val meal: Meal) : MealResult<Meal>()
data class MealError(val message: String) : MealResult<Nothing>()

fun addMeal(name: String, calories: Int, ingredients: List<String>, mealDatabase: MealDatabase): MealResult<Meal> {
    return try {
        if (name.isBlank() || ingredients.isEmpty()) {
            return MealResult.MealError("Meal name or ingredients cannot be blank")
        }

        val newMeal = Meal(1, name, calories, ingredients)
        val mealId = mealDatabase.insertMeal(trackerModel(mealName = name, ingredients = ingredients.joinToString(", "), calories = calories, carbonfootprint = 0))

        if (mealId != -1L) {
            MealResult.MealAdded(newMeal.copy(id = mealId.toInt()))
        } else {
            MealResult.MealError("Error adding meal to the database")
        }
    } catch (e: Exception) {
        MealResult.MealError("Error adding meal: ${e.message}")
    }
}
private fun Nothing?.isEmpty(): Boolean {
    return null == true
}

fun updateMeal(meal: Meal, newName: String, newCalories: Int, mealDatabase: MealDatabase): MealResult<Meal> {
    return try {
        val updatedMealModel = trackerModel(id = meal.id, mealName = newName, ingredients = meal.ingredients.joinToString(", "), calories = newCalories, carbonfootprint = 0)
        val rowsAffected = mealDatabase.updateMeal(updatedMealModel)

        if (rowsAffected > 0) {
            val updatedMeal = Meal(updatedMealModel.id, updatedMealModel.mealName, updatedMealModel.calories, updatedMealModel.ingredients.split(", "))
            MealResult.MealUpdated(updatedMeal)
        } else {
            MealResult.MealError("Error updating meal in the database")
        }
    } catch (exception: Exception) {
        MealResult.MealError("Error updating meal: ${exception.message}")
    }
}

fun searchMeals(query: String, mealDatabase: MealDatabase): List<Meal> {
    val lowercaseQuery = query.toLowerCase()
    val mealsFromDatabase = mealDatabase.getAllMeals()

    return mealsFromDatabase.filter { it.mealName.toLowerCase().contains(lowercaseQuery) }
        .map { Meal(it.id, it.mealName, it.calories, it.ingredients.split(", ")) }
} */


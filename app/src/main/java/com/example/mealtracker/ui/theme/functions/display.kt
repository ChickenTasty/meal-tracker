package com.example.mealtracker.ui.theme.functions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealtracker.R

class MealAdapter(private val meals: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val ingredientsTextView: TextView = itemView.findViewById(R.id.ingredientsTextView)
        val caloriesTextView: TextView = itemView.findViewById(R.id.caloriesTextView)
    }
w
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item_layout, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentMeal = meals[position]

        holder.nameTextView.text = "Name: ${currentMeal.name}"
        holder.ingredientsTextView.text = "Ingredients: ${currentMeal.ingredients.joinToString(", ")}"
        holder.caloriesTextView.text = "Calories: ${currentMeal.calories}"
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}





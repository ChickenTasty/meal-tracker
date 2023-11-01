package com.example.mealtracker.ui.theme.functions

sealed class screen(val route: String){
    object HomePage : screen(route = "Home_Page")
    object HomePage2 : screen(route = "Home_Page#2")
    object ProgressPage : screen(route = "Progress_Page")
    object ProgressPage2 : screen(route = "Progress_Page#2")
    object ArchivePage : screen(route = "Archive_Page")

}

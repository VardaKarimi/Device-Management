package component

import navigations.NavController
import screens.Screen
import java.util.prefs.Preferences


fun logout(navController: NavController){
    val preferences = Preferences.userRoot().node("com.example.myapplication")

    navController.navigate(Screen.LogInScreen.name)
    preferences.remove("userEmail")


}
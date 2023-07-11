package screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens
{
    LogInScreen,
    Home,
    Screen1,
    Screen2,
    Screen3
}/**
 * Screens
 */
enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    AddDeviceScreen(
        label = "Add Device",
        icon = Icons.Filled.Add
    ),
    AssignDeviceScreen(
        label = "Assign Device",
        icon = Icons.Filled.Assignment
    ),
    LogInScreen(
        label = "LogInScreen",
        icon = Icons.Filled.VerifiedUser
    )

}
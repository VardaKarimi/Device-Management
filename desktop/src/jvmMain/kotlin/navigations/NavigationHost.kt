package navigations

import androidx.compose.runtime.Composable

/**
 * NavigationHost class
 */
class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {


    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController
    )
}


/**
 * Composable to build the Navigation Host
 */
@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: String,
    content: @Composable () -> Unit
) {
    if (navController.currentScreen.value == route) {
        content()
    }
}
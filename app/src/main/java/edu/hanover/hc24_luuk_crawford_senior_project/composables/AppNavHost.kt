package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.MenuScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.ToppingsScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.WelcomeScreen
import edu.hanover.hc24_luuk_crawford_senior_project.data.Destination
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.getCurrentUserOrder

/**
 * Nav host is the composable navigation manager.
 * Handles which composable to load when triggered
 * It is set up like this to prevent giving navController itself to everything.
 * @param startDestination Which composable to start at (string). Defaults to WelcomeScreen.
 * @param navController Handles navigation navController.navigate (Destination name). Defaults rememberNavController()
 */
@Composable
fun AppNavHost(
    startDestination: String = Destination.welcomeScreen.name,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        val onNavigateToMenuFunction =
            { navController.navigate(Destination.menuScreen.name) }
        val onNavigateToToppingsFunction =
            { navController.navigate(Destination.toppingsScreen.name) }
        val onNavigateToWelcomeScreen = {
            navController.navigate(Destination.welcomeScreen.name)
        }

        composable(Destination.welcomeScreen.name) { WelcomeScreen(onNavigateToMenuFunction) }

        composable(Destination.menuScreen.name) { MenuScreen(onNavigateToToppingsFunction) }

        composable(Destination.toppingsScreen.name) { ToppingsScreen(onNavigateToWelcomeScreen, getCurrentUserOrder()) }
    }
}

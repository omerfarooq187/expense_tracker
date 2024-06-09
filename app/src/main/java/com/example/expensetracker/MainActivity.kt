package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.screens.auth.LoginScreen
import com.example.expensetracker.screens.auth.SignupScreen
import com.example.expensetracker.screens.auth.WelcomeScreen
import com.example.expensetracker.screens.main.DashboardScreen
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme
import com.example.expensetracker.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setContent {
            ExpenseTrackerTheme {
                App(authViewModel)
            }
        }
    }
}

@Serializable
object WelcomeScreenRoute
@Serializable
object SignupScreenRoute
@Serializable
object LoginScreenRoute
@Serializable
object DashBoardScreenRoute

@Composable
fun App(viewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WelcomeScreenRoute) {
        composable<WelcomeScreenRoute> {
            WelcomeScreen(viewModel, navController)
        }
        composable<LoginScreenRoute> {
            LoginScreen(viewModel, navController)
        }
        composable<SignupScreenRoute> {
            SignupScreen(viewModel, navController)
        }
        composable<DashBoardScreenRoute> {
            DashboardScreen()
        }
    }
}

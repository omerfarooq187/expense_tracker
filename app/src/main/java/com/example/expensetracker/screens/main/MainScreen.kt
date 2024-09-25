package com.example.expensetracker.screens.main

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.ExpenseScreenRoute
import com.example.expensetracker.IncomeScreenRoute
import com.example.expensetracker.R

sealed class BottomNavItems(val route: String, val icon: ImageVector, val title: String) {
    data object Home : BottomNavItems("home", Icons.Default.Home, "Home")
    data object Transaction : BottomNavItems("transaction", Icons.AutoMirrored.Default.CompareArrows, "Transaction")
    data object Budget: BottomNavItems("budget", Icons.Default.MonetizationOn, "Budget")
    data object Profile : BottomNavItems("profile", Icons.Default.AccountCircle, "Profile")
}

private val navItems = listOf(
    BottomNavItems.Home,
    BottomNavItems.Transaction,
    BottomNavItems.Budget,
    BottomNavItems.Profile
)

@Composable
fun MainScreen(mainNavController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        floatingActionButton = {
            SpeedDailFab(mainNavController)
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {

            NavigationBar(
                containerColor = Color.White,
            ) {
                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = item.route == currentRoute,
                        onClick = {
                            item.route.let { route ->
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = ""
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = Color("#9C27B0".toColorInt()),
                            selectedIconColor = Color("#9C27B0".toColorInt()),
                            unselectedTextColor = Color.Gray,
                            unselectedIconColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavHost(navController = navController, startDestination = BottomNavItems.Home.route) {
                composable(BottomNavItems.Home.route) {
                    DashboardScreen()
                }
                composable(BottomNavItems.Transaction.route) {
                    TransactionScreen()
                }
                composable(BottomNavItems.Budget.route) {
                    BudgetScreen()
                }
                composable(BottomNavItems.Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun SpeedDailFab(mainNavController: NavController) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isExpanded) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .animateContentSize()
                    .padding(end = 16.dp, bottom = 90.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        mainNavController.navigate(IncomeScreenRoute)
                    },
                    containerColor = Color("#00A86B".toColorInt()),
                ) {
                    Image(
                        painter = painterResource(R.drawable.income),
                        contentDescription = "Income"
                    )
                }

                FloatingActionButton(
                    onClick = { /* Handle Transaction click */ },
                    containerColor = Color("#9C27B0".toColorInt()),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.CompareArrows,
                        contentDescription = "Transaction",
                        tint = Color.White
                    )
                }

                FloatingActionButton(
                    onClick = {
                        mainNavController.navigate(ExpenseScreenRoute)
                    },
                    containerColor = Color("#FD3C4A".toColorInt()),
                    modifier = Modifier
                ) {
                    Image(
                        painter = painterResource(R.drawable.expense),
                        contentDescription = "expense"
                    )
                }
            }
        }
        // Main FAB
        FloatingActionButton(
            onClick = { isExpanded = !isExpanded }, // Toggle expansion
            shape = CircleShape,
            containerColor = Color("#9C27B0".toColorInt()),
            contentColor = Color.White,
            modifier = Modifier
                .padding(16.dp)
//                .animateContentSize()
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Main FAB"
            )
        }
    }
}
package com.example.expensetracker.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController

@Composable
fun ExpenseScreen(mainNavController: NavController) {
    var expenseAmount by remember {
        mutableStateOf("0")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#FD3C4A".toColorInt()))
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderSection("Expense") {
                mainNavController.popBackStack()
            }

            Column {
                AmountSection(expenseAmount) {
                    expenseAmount = it
                }
                FormSection {
                    ExpenseDropdownField(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDropdownField(placeholder: String) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = "",
            readOnly = true,
            onValueChange = {},
            placeholder = {
                Text(text = placeholder)
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            repeat(5) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Item $it")
                    },
                    onClick = {
                        isExpanded = false

                    },
                )
            }
        }
    }
}
package com.example.expensetracker.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController

@Composable
fun IncomeScreen(mainNavController: NavController) {
    var incomeAmount by remember {
        mutableStateOf("0")
    }
    var category by remember {
        mutableStateOf("")
    }
    var wallet by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#00A86B".toColorInt()))
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header section
          HeaderSection(title = "Income") {
              mainNavController.popBackStack()
          }

            Column {

                // Amount Section
                AmountSection(incomeAmount) {incomeAmount=it}

                Spacer(modifier = Modifier.height(20.dp))

                // Form Section
                FormSection { placeHolder ->
                    IncomeDropdownField(placeHolder) {
                        when(it) {
                            "Category"->category=it
                            "Wallet"->wallet=it
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeDropdownField(placeholder: String, valueChange:(String)->Unit) {
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
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = placeholder)
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
            when(placeholder) {
             "Category"-> {
                 categories.forEach {
                     DropdownMenuItem(
                         text = {
                             Text(text = it)
                         },
                         onClick = {
                             isExpanded = false
                             valueChange(it)
                         },
                     )
                 }
             }
             "Wallet"-> {
                 walletItems.forEach {
                     DropdownMenuItem(
                         text = {
                             Text(text = it)
                         },
                         onClick = {
                             isExpanded = false
                             valueChange(it)
                         },
                     )
                 }
             }
            }
        }
    }
}


val categories = listOf(
    "Salary",
    "Freelancing",
    "Business"
)

val walletItems = listOf(
    "Paypal",
    "Master Card",
    "Easypaisa",
    "Jazz Cash"
)
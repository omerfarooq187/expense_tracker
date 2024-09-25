package com.example.expensetracker.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.expensetracker.R


@Composable
fun HeaderSection(title:String, navigationBack:()->Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navigationBack()
                }
        )

        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins))
        )
        Spacer(modifier = Modifier.width(18.dp))
    }
}


@Composable
fun FormSection(dropdownField: @Composable (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Color("#FCFCFC".toColorInt()))
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        dropdownField("Category")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    "Description"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        dropdownField("Wallet")

        Spacer(modifier = Modifier.height(20.dp))

        // Attachment button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Attachment, // Replace with actual attachment icon
                contentDescription = "Add Attachment",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add attachment",
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Repeat switch
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Repeat",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = false,
                onCheckedChange = { /* TODO: Handle repeat */ }
            )
        }
        Button(
            onClick = { /* Handle continue */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color("#7F3DFF".toColorInt())),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(56.dp)
        ) {
            Text(text = "Continue", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun AmountSection(
    incomeAmount: String,
    onAmountChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
    ) {
        Text(
            text = "How much?",
            color = Color("#616161".toColorInt()),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

        // Ensure the dollar sign is always present
        val amountWithDollarSign = remember(incomeAmount) {
            if (incomeAmount.startsWith("$")) incomeAmount else "$$incomeAmount"
        }

        TextField(
            value = amountWithDollarSign,
            onValueChange = {
                // Remove any dollar signs entered by the user (if they try to add more)
                var cleanedInput = it.replace("$", "")

                if (cleanedInput.startsWith("0") && cleanedInput.length > 1) {
                    cleanedInput = cleanedInput.substring(1)
                }

                // Ensure the dollar sign is always at the beginning
                onAmountChange("$$cleanedInput")
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


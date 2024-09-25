package com.example.expensetracker.screens.main

import android.icu.util.Currency
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.expensetracker.R

@Composable
fun DashboardScreen() {
    val month by remember {
        mutableStateOf("June")
    }
    val accountBalance by remember {
        mutableStateOf("9400")
    }
    val income by remember {
        mutableStateOf("5000")
    }
    val expenses by remember {
        mutableStateOf("1200")
    }
    val dollarSign = Currency.getInstance("USD").symbol
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.picture),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .border(2.dp, Color.Magenta, RoundedCornerShape(50.dp))
                    .weight(0.2f)
            )

            Text(
                text = month,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "",
                modifier = Modifier
                    .weight(0.2f)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Account Balance",
                color = Color.LightGray,
                fontWeight = FontWeight(450),
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 6.dp)
            )
            Text(
                text = dollarSign + accountBalance,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            //Income
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(160.dp)
                    .height(85.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color("#00ab41".toColorInt()))
            ) {
                Image(
                    painter = painterResource(R.drawable.income),
                    contentDescription = "",
                    modifier = Modifier
                        .size(65.dp)
                        .padding(end = 10.dp)
                )
                Column {
                    Text(
                        text = "Income",
                        color = Color.White
                    )
                    Text(
                        text = income + dollarSign,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins))
                    )
                }
            }

            //Expenses
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(160.dp)
                    .height(85.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color("#008631".toColorInt()))
            ) {
                Image(
                    painter = painterResource(R.drawable.expense),
                    contentDescription = "",
                    modifier = Modifier
                        .size(65.dp)
                        .padding(end = 10.dp)
                )
                Column {
                    Text(
                        text = "Expenses",
                        color = Color.White
                    )
                    Text(
                        text = expenses + dollarSign,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val frequencies = listOf(
            34,23,45,11,44,56
        )
        FrequencyBarChart(frequencies)
        Spacer(modifier = Modifier.height(16.dp))
        RecentTransactionSection()
        TransactionItem()
    }
}


@Composable
fun FrequencyBarChart(frequencies:List<Int>) {
    Column(
       modifier = Modifier
           .fillMaxWidth()
    ){
        Text(
            text = "Speed Frequency",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
        ) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(16.dp)) {

                val path = androidx.compose.ui.graphics.Path()
                val waveHeight = size.height / 2
                val waveWidth = size.width / (frequencies.size * 2)

                path.moveTo(0f, waveHeight)

                // Iterate over frequencies to create wave points
                for (i in frequencies.indices) {
                    val x1 = i * 2 * waveWidth
                    val y1 = waveHeight - (frequencies[i].toFloat() / frequencies.maxOrNull()!! * waveHeight)
                    val x2 = (i * 2 + 1) * waveWidth
                    val y2 = waveHeight + (frequencies[i].toFloat() / frequencies.maxOrNull()!! * waveHeight)

                    // Create a sine wave-like pattern
                    path.quadraticBezierTo(x1, y1, x2, y2)
                }

                drawPath(path, color = Color.Blue, style = Stroke(width = 4f))
            }
        }
        FrequencyTypes()
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//
//        }
    }
}

@Composable
fun FrequencyTypes() {
    var selectedItem by remember { mutableStateOf("Today") }

    // List of items
    val timeFilters = listOf("Today", "Week", "Month", "Year")

    // Row to hold the items
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Center vertically
    ) {
        // Iterate over the list of items
        timeFilters.forEach { filter ->
            // Define whether this item is selected
            val isSelected = filter == selectedItem

            // Each item in the row
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50)) // Rounded corners
                    .background(
                        if (isSelected) Color.LightGray else Color.Transparent
                    ) // Light background for selected
                    .clickable {
                        // Handle click, update selected item
                        selectedItem = filter
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp), // Internal padding
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = filter,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color("#41C300".toColorInt()) else Color.Gray // Brighten selected text
                )
            }
        }
    }
}


@Composable
fun RecentTransactionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Recent Transactions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "See All",
                    color = Color("#916eff".toColorInt()),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color("#f2fde4".toColorInt()))
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingBag,
                contentDescription = "",
                tint = Color("#41C300".toColorInt()),
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Shopping",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400)
                )
                Text(
                    text = "-120$",
                    color = Color.Red,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            Spacer(modifier=Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Buy some grocery",
                    color = Color.LightGray,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )
                Text(
                    text = "10:00 AM",
                    color = Color.LightGray,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )

            }
        }
    }
}
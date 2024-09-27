package dev.com.mritservices.vendingmachineproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    brandName: String,
    onRedeemClick: () -> Unit,
    onCheckBalanceClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Welcome to $brandName",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Please scan your Staff Pass to check balance limit or redeem your Item",
                fontSize = 16.sp,
                modifier = Modifier.padding(12.dp)
            )

            // Buttons for Redeem and Check Balance
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onRedeemClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(0.2f) // Adjust the height as needed
                ) {
                    Text(text = "Redeem")
                }

                Button(
                    onClick = onCheckBalanceClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(0.2f) // Same height for Check Balance button
                ) {
                    Text(text = "Check Balance")
                }
            }

            // Instructions
            Text(
                text = "Please Choose your selection",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

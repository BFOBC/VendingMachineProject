package dev.com.mritservices.vendingmachineproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckBalanceScreen(balance: String, uniformCount: Int, pantsCount: Int, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please Tap your Staff Pass to Check Balance", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Tap your Staff Pass to proceed.", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Your Credit Balance:", style = MaterialTheme.typography.labelLarge)
        Text(text = "Uniform: $uniformCount", style = MaterialTheme.typography.labelLarge)
        Text(text = "Pants: $pantsCount", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack) {
            Text(text = "Thank you")
        }
    }
}

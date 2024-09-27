import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.com.mritservices.vendingmachineproject.models.VendModel
import dev.com.mritservices.vendingmachineproject.viewmodels.OrderViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun DispenseItemTest(viewModel: OrderViewModel = viewModel()) {
    var slotNumber by remember { mutableStateOf(TextFieldValue("")) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }
    var isValid by remember { mutableStateOf(true) }
    var validationMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Validation function
    fun isValidated(): Boolean {
        return when {
            slotNumber.text.isEmpty() -> {
                validationMessage = "Enter slot number"
                false
            }
            quantity.text.isEmpty() -> {
                validationMessage = "Enter quantity"
                false
            }
            else -> true
        }
    }

    // Vend order function using ViewModel
    fun vendOrder(item: VendModel) {
        coroutineScope.launch {
            // Simulate port connection delay
            viewModel.openAndConnectPort()

            // Vend items after port connection
            delay(1000) // Simulating a delay before starting to vend

            val itemQuantity = item.quantity
            for (i in 1..itemQuantity) {
                val result = viewModel.testVend(item)

                // Show a toast with the result (mimicking Android Toast)
                Toast.makeText(context, "Slave Machine Shipped: $result", Toast.LENGTH_SHORT).show()

                // Delay before vending the next item (30 seconds)
                delay(30000)
            }

            // Show success message after all items are vended
            validationMessage = "Items successfully dispensed."
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Please select your item",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Slot number input
            OutlinedTextField(
                value = slotNumber,
                onValueChange = { slotNumber = it },
                label = { Text("Enter slot") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Quantity input
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Enter quantity") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Validation message
            if (!isValid) {
                Text(text = validationMessage, color = androidx.compose.ui.graphics.Color.Red)
            }

            // Button to dispense item
            Button(onClick = {
                if (isValidated()) {
                    val item = VendModel(slotNumber.text.toInt(), quantity.text.toInt())
                    vendOrder(item)
                } else {
                    isValid = false
                }
            }) {
                Text("Dispense")
            }

            // Display success message after dispensing
            if (validationMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Text(text = validationMessage)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DispenseItemTestPreview() {
    DispenseItemTest()
}

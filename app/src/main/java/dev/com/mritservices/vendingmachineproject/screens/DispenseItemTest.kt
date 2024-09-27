package dev.com.mritservices.vendingmachineproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DispenseItemTest(){
     var slotNumber by remember { mutableStateOf(TextFieldValue("")) }
     
     Box(
          modifier = Modifier
                  .fillMaxSize()
                  .padding(16.dp), // Add padding around the content
          contentAlignment = Alignment.Center
        ) {
          Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center
                ) {
               // Centered text
               Text(
                    text = "Please select your item",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp) // Space below the text
                   )
               
               OutlinedTextField(
                    value = slotNumber,
                    onValueChange = { slotNumber = it },
                    label = { Text("Enter slot") }, // Hint with label
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                                )
               
               // Button to dispense item
               Button(onClick = { /* Dispense action here */ }) {
                    Text("Dispense")
               }
          }
     }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DispenseItemTestPreview(){
     
     DispenseItemTest()
}
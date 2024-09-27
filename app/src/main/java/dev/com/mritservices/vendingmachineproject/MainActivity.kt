package dev.com.mritservices.vendingmachineproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.com.mritservices.vendingmachineproject.navigation.Navigator
import dev.com.mritservices.vendingmachineproject.ui.theme.VendingMachineProjectTheme

class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState : Bundle?) {
          super.onCreate(savedInstanceState)
          enableEdgeToEdge()
          setContent {
               VendingMachineProjectTheme {
                    Navigator()
               }
          }
     }
}



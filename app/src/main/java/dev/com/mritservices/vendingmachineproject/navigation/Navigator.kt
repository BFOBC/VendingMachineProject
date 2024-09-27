package dev.com.mritservices.vendingmachineproject.navigation

import DispenseItemTest
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.com.mritservices.vendingmachineproject.screens.CheckBalanceScreen
import dev.com.mritservices.vendingmachineproject.screens.RedeemItemScreen
import dev.com.mritservices.vendingmachineproject.screens.SplashScreen
import dev.com.mritservices.vendingmachineproject.screens.VerificationFailedScreen
import dev.com.mritservices.vendingmachineproject.screens.VerificationSuccessScreen
import dev.com.mritservices.vendingmachineproject.screens.WelcomeScreen
import dev.com.mritservices.vendingmachineproject.utility.Constant

@Composable
fun Navigator(){
     Constant.navController = rememberNavController()
     NavHost(navController = Constant.navController as NavHostController, startDestination = Route.SplashScreen.value) {
          composable(route = Route.SplashScreen.value) {
               SplashScreen()
          }

          composable(route = Route.WelcomeScreen.value) {
               WelcomeScreen(
                    brandName = "Test ABC",
                    onRedeemClick = {
                         Constant.navController?.navigate(Route.RedeemItemScreen.value)
                    },
                    onCheckBalanceClick = {
                         Constant.navController?.navigate(Route.CheckBalanceScreen.value) // Navigating to DetailsScreen
                    }
               )
          }

          composable(route = Route.RedeemItemScreen.value) {
               RedeemItemScreen(
                    onSelectItem = { item ->
                         // Handle item selection
                    },
                    onBack = {
                         // Handle back navigation
                    },
                    availableItems = listOf("Item 1", "Item 2", "Item 3") // Dummy data
               )
          }

          composable(route = Route.CheckBalanceScreen.value) {
               CheckBalanceScreen(
                    balance = "100", // Dummy balance
                    uniformCount = 2, // Dummy uniform count
                    pantsCount = 3,   // Dummy pants count
                    onBack = {
                         // Handle back navigation
                    }
               )
          }


          composable(route = Route.DispenseItemTest.value) {
               DispenseItemTest()
          }

          composable(route = Route.VerificationFailedScreen.value) {
               VerificationFailedScreen(
                    onRetry = {
                         Constant.navController?.navigate(Route.RedeemItemScreen.value)
                    }
               )
          }

          composable(route = Route.VerificationSuccessScreen.value) {
               VerificationSuccessScreen(
                    onSelectItem = {
                         Constant.navController?.navigate(Route.RedeemItemScreen.value)
                    }
               )
          }
     }
}

package dev.com.mritservices.vendingmachineproject.navigation

sealed class Route(val value: String) {
     object SplashScreen : Route("Splash")
     object WelcomeScreen : Route("WelcomeScreen")
     object DispenseItemTest : Route("DispenseItemTest")
     object HomeScreen : Route("Home")
     object EnrollScreen : Route("EnrollScreen")
     object PrePostSelectionScreen : Route("PrePostSelectionScreen")
     object EnterIdScreen : Route("EnterIdScreen")
     object DetailsScreen : Route("DetailsScreen")
     object BioMetricVerificationScreen : Route("BioMetricVerificationScreen")
     object PaymentSelectionScreen : Route("PaymentSelectionScreen")
     object SuccessScreen : Route("SuccessScreen")
     object CashScreen : Route("CashScreen")
     object VendingScreen : Route("VendingScreen")
     object PrinterScreen : Route("PrinterScreen")
     object RedeemItemScreen : Route("RedeemItemScreen")
     object CheckBalanceScreen : Route("CheckBalanceScreen")
 
}
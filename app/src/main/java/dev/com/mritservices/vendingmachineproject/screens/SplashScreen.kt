package dev.com.mritservices.vendingmachineproject.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dev.com.mritservices.vendingmachineproject.R
import dev.com.mritservices.vendingmachineproject.utility.Constant.navController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

     LaunchedEffect(key1 = true) {
          delay(3000) // Wait for 3 seconds
          navController?.navigate("WelcomeScreen") // Navigate to the next screen
     }


     Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Image(
               painter = painterResource(id = R.drawable.splashicon), // Replace with your image resource
               contentDescription = "Splash Image",
               contentScale = ContentScale.Fit,
               modifier = Modifier.size(200.dp) // Adjust size as needed
               )
     }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashPreview(){

     SplashScreen()

}
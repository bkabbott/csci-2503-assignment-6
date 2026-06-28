package edu.georgiasouthern.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.georgiasouthern.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                AppNav()
            }
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination="splash"
    ) {
        composable("splash") {
            SplashScreen(
                onContinue = { navController.navigate("phone") }
            )
        }
        composable("phone") {
            PhoneScreen(
                onBack= { navController. popBackStack() }
            )
        }
    }
}

@Composable
fun PhoneScreen(onBack: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(horizontal = 30.dp, vertical = 43.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = painterResource(R.drawable.tube_foreground),
            contentDescription = "Test tube",
            modifier = Modifier.size(72.dp)
        )
        Spacer(Modifier.height(43.dp))
        Text(
            color = Color(0xFF001B2D),
            text="Welcome",
            fontSize = 32.sp
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text="Sign in to continue",
            color= Color(0xFF77869e)
        )
    }
}

@Composable
fun SplashScreen(onContinue: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF008FD9))
            .safeDrawingPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Image(
            painter = painterResource(R.drawable.tube_foreground),
            contentDescription="Test tube",
            modifier = Modifier.size(72.dp)
        )
        Spacer(Modifier.height(32.dp))
        Text(
            text="Operator",
            color=Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text="Complete your daily worksheet\nScan it with Amazon Textract\nPush it to the cloud",
            color=Color.White.copy(alpha = 0.9f),
            fontSize=16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max=260.dp)
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onContinue,
            Modifier.width(315.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001B2D))
        ) {
            Text("Get Started")
        }

        Spacer(Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhoneScreen()
}
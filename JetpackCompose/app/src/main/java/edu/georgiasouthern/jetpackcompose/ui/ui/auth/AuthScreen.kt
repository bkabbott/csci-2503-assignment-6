package edu.georgiasouthern.jetpackcompose.ui.ui.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkBlue1 = Color(0xFF0D1B2A)
private val DarkBlue2 = Color(0xFF1B3A5C)
private val DarkBlue3 = Color(0xFF0D2137)
private val AccentBlue = Color(0xFF4FC3F7)
private val GlassWhite = Color.White.copy(alpha = 0.10f)
private val BorderWhite = Color.White.copy(alpha = 0.20f)

@Composable
fun AuthScreen(onAuthenticated: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var isCodeSent by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun fakeSendSms() {
        isCodeSent = true
        errorMessage = null
    }

    fun fakeLogin() {
        if (pin.isBlank()) {
            errorMessage = "Please enter the SMS code"
        } else {
            errorMessage = null
            onAuthenticated()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DarkBlue1, DarkBlue2, DarkBlue3)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = GlassWhite),
            border = BorderStroke(1.dp, BorderWhite)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Consolidated Utilities",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Operator Reporting",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Sign in",
                    color = Color.White.copy(alpha = 0.70f),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                val textFieldColors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White.copy(alpha = 0.50f),
                    focusedBorderColor = AccentBlue,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.70f),
                    focusedLabelColor = AccentBlue,
                    cursorColor = AccentBlue,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth()
                )

                AnimatedVisibility(
                    visible = isCodeSent,
                    enter = fadeIn() + slideInVertically()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = pin,
                            onValueChange = { pin = it },
                            label = { Text("SMS Code") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            colors = textFieldColors,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { if (isCodeSent) fakeLogin() else fakeSendSms() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                ) {
                    Text(
                        text = if (isCodeSent) "Login" else "Send SMS",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = errorMessage!!,
                        color = Color(0xFFFF5252),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

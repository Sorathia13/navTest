package com.example.navtest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun layoutThree(navController: NavHostController, montantTTC: String) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Montant TTC",
            fontSize = 30.sp,
            color = Color.Black
        )
        Spacer(
            modifier = Modifier.height(65.dp)
        )
        TextField(
            value = montantTTC,
            onValueChange = {},
            label = { Text("Montant TTC") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { /* Dismiss keyboard */ }
            ),
            modifier = Modifier.padding(bottom = 8.dp),
            enabled = false
        )
        Spacer(
            modifier = Modifier.height(65.dp)
        )
        Button(onClick = {
            navController.navigate("Layout2")
        }) {
            Text(text="Retour", fontSize = 18.sp)
        }
    }
}
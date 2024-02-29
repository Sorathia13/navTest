package com.example.navtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun layoutOne(navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var context = LocalContext.current

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Authentification",
                modifier = Modifier.padding(bottom = 20.dp),
                fontSize = 30.sp
            )
            // Zone de saisie pour le login
            TextField(
                value = login,
                onValueChange = { login = it },
                placeholder = { Text("Login") },
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Zone de saisie pour le mot de passe
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Mot de passe") },
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Bouton de connexion
            Button(
                onClick = {
                    if (login == "etudiant" && password == "AzertY") {
                        Toast.makeText(
                            context,
                            "Connexion réussie",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate("Layout2")
                    } else {
                        Toast.makeText(
                            context,
                            "Nom d'utilisateur ou mot de passe incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Connexion")
            }
        }
    }
}
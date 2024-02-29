package com.example.navtest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun layoutTwo(navController: NavHostController) {
    var quantite by remember { mutableStateOf("") }
    var prixUnitaire by remember { mutableStateOf("") }
    var tauxTVA by remember { mutableStateOf("") }
    var fidelite by remember { mutableStateOf(false) }
    var remise by remember { mutableStateOf("") }
    var montantHT by remember { mutableStateOf("") }
    var montantTTC by remember { mutableStateOf("") }
    val context = LocalContext.current

    var isButtonEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(quantite, prixUnitaire, tauxTVA) {
        isButtonEnabled = quantite.isNotEmpty() && prixUnitaire.isNotEmpty() && tauxTVA.isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Facture",
            modifier = Modifier.padding(bottom = 20.dp),
            fontSize =  30.sp,
        )
        TextField(
            value = quantite,
            onValueChange = { quantite = it; montantHT = calculMontantHT(it, prixUnitaire) },
            label = { Text("Quantité") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Dismiss keyboard */ }
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = prixUnitaire,
            onValueChange = { prixUnitaire = it; montantHT = calculMontantHT(quantite, it) },
            label = { Text("Prix unitaire") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Dismiss keyboard */ }
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = montantHT,
            onValueChange = {},
            label = { Text("Montant HT") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { /* Dismiss keyboard */ }
            ),
            modifier = Modifier.padding(bottom = 8.dp),
            enabled = false
        )
        TextField(
            value = tauxTVA,
            onValueChange = { tauxTVA = it; },
            label = { Text("Taux TVA") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Dismiss keyboard */ }
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Êtes-vous fidèle?")
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = fidelite,
                onCheckedChange = { fidelite = it },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        TextField(
            value = remise,
            onValueChange = { remise = it },
            label = { Text("Remise") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Dismiss keyboard */ }
            ),
            modifier = Modifier.padding(bottom = 8.dp),
            enabled = fidelite
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    montantTTC = calculMontantTTC(montantHT, tauxTVA, remise)
                    navController.navigate("Layout3/$montantTTC")
                },
                enabled = isButtonEnabled,
            ) {
                Text("Calculer TTC")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    quantite = ""
                    prixUnitaire = ""
                    tauxTVA = ""
                    remise = ""
                    montantHT = ""
                }
            ) {
                Text("Remise à zéro")
            }
        }
    }
}

fun calculMontantHT(quantite: String, prixUnitaire: String): String {
    return if (quantite.isNotEmpty() && prixUnitaire.isNotEmpty()) {
        val quantiteDouble = quantite.toDouble()
        val prixUnitaireDouble = prixUnitaire.toDouble()

        (quantiteDouble * prixUnitaireDouble).toString()
    } else {
        ""
    }
}

fun calculMontantTTC(montantHT: String, tauxTVA: String, remise: String): String {
    return if (montantHT.isNotEmpty() && tauxTVA.isNotEmpty()) {
        val montantHTDouble = montantHT.toDouble()
        val tauxTVADouble = tauxTVA.toDouble()

        val montantTTCCalcul = montantHTDouble + (montantHTDouble * tauxTVADouble / 100)

        if (remise.isNotEmpty()) {
            val remiseDouble = remise.toDouble()
            val montantRemise = montantTTCCalcul - (montantTTCCalcul * remiseDouble / 100)
            return montantRemise.toString()
        } else {
            return montantTTCCalcul.toString()
        }
    } else {
        ""
    }
}

package com.nexo.app.nexo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexo.app.nexo.viewModel.LoginViewModel

@Composable
    fun LoginScreen(
        viewModel: LoginViewModel,
        onIrARegistro: () -> Unit, // Acción para ir a registro
        onIrAHome: () -> Unit      // Acción para entrar a la app
    ) {
        var correo by remember { mutableStateOf("") }
        var pin by remember { mutableStateOf("") }
        val exito by viewModel.loginExitoso.collectAsState()

        if (exito == true) {
            LaunchedEffect(Unit) { onIrAHome() }
        }

        Column (modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Iniciar Sesión", style = MaterialTheme .typography.headlineMedium)

            OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
            OutlinedTextField(value = pin, onValueChange = { pin = it }, label = { Text("PIN") })

            Button(onClick = { viewModel.iniciarSesion(correo, pin) }) { Text("Entrar") }

            // OPCIONES ADICIONALES
            TextButton(onClick = onIrARegistro) { Text("¿No tienes cuenta? Regístrate") }
            TextButton(onClick = { /* Lógica de recuperar */ }) { Text("Olvidé mi PIN") }
        }
    }


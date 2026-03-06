package com.nexo.app.nexo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import nexo.composeapp.generated.resources.Res
import nexo.composeapp.generated.resources.compose_multiplatform
//------------------
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nexo.app.nexo.model.datasource.DataSource
import com.nexo.app.nexo.model.dto.UserDTO
import com.nexo.app.nexo.ui.screens.RegistroScreen
import com.nexo.app.nexo.viewModel.RegistroViewModel
import com.nexo.app.nexo.model.repository.UserRepository
import com.nexo.app.nexo.ui.screens.LoginScreen
import com.nexo.app.nexo.viewModel.LoginViewModel

@Composable
@Preview
fun App() {
    //Se define la pantalla en que estamos, en este caso es: "Login"
    var pantallaActual by remember { mutableStateOf("login") }
        MaterialTheme {
            val repository = UserRepository(dataSource = UserDataSourceImpl())

            when (pantallaActual){
                val loginVM = LoginViewModel(repository)
                "login" -> {
                LoginScreen(
                    viewModel = loginVM,
                    onIrARegistro = {pantallaActual = "registro"},
                    onIrAHome = {pantallaActual = "home"}
                )
            }
            "registro" -> {
                val registroVM = RegistroViewModel(repository)
            RegistroScreen(viewModel = registroVM)
            //Añadir un boton para volver al login o al registrarse direccionar al login
        }
        }

            var showContent by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
            //1.- Instamciamos la cadena de responsabilidades(Temporalmente manual)
            //Sustituir 'UserDataSourceImpl()' por la clase real de datos
            val repository = UserRepository(dataSource = UserDataSourceImpl())
            val registroViewModel = RegistroViewModel(repository = repository)

            //2.- Llamamos a la pantalla y le pasamos el ViewModel
            RegistroScreen(viewModel = registroViewModel)
        }
    }

    class UserDataSourceImpl : DataSource.UserRemoteDataSource{
        override suspend fun registerUser(user: UserDTO): Result<UserDTO> {
            //Simulando registro siempre exitoso por ahora
            return Result.success(user)
        }
    }
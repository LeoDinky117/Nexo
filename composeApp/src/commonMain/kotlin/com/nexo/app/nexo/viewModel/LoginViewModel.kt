package com.nexo.app.nexo.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexo.app.nexo.model.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {
    private val _loginExitoso = MutableStateFlow<Boolean?>(null)
    val loginExitoso: StateFlow<Boolean?> = _loginExitoso

    fun iniciarSesion(correo: String, pin: String){
        viewModelScope.launch {
            //Se llama al repositorio
            _loginExitoso.value = true
        }
    }
}
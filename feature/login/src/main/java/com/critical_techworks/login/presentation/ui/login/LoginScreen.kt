package com.critical_techworks.login.presentation.ui.login

import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.critical_techworks.login.presentation.theme.BiometricAuthenticationError
import com.critical_techworks.login.presentation.ui.util.BiometricAuthListener
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@Composable
fun LoginScreen(navigateToList: () -> Unit) {

    val context = LocalContext.current
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val biometricAuthListener: BiometricAuthListener = object :
        BiometricAuthListener {
        override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
            navigateToList()
        }

        override fun onBiometricAuthenticationFailed(errorCode: Int, errorMessage: String) {
            Toast.makeText(
                context,
                BiometricAuthenticationError + errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onBiometricAuthenticationNotFound() {
            navigateToList()
        }
    }

    loginViewModel.checkBiometricAuthentication(context, biometricAuthListener)
}
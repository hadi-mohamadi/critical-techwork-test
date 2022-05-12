package com.critical_techworks.login.presentation.ui.util

import androidx.biometric.BiometricPrompt

interface BiometricAuthListener {
    fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult)
    fun onBiometricAuthenticationFailed(errorCode: Int, errorMessage: String)
    fun onBiometricAuthenticationNotFound()
}
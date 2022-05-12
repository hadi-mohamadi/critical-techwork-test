package com.critical_techworks.login.presentation.ui.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.critical_techworks.login.presentation.ui.util.BiometricAuthListener
import com.critical_techworks.login.presentation.ui.util.BiometricUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val biometricUtil: BiometricUtil) : ViewModel() {

    internal fun checkBiometricAuthentication(
        context: Context,
        biometricAuthListener: BiometricAuthListener
    ) {
        if (biometricUtil.isBiometricReady(context)) {
            biometricUtil.showBiometricPrompt(
                activity = context as AppCompatActivity,
                listener = biometricAuthListener
            )
        } else {
            biometricAuthListener.onBiometricAuthenticationNotFound()
        }
    }
}
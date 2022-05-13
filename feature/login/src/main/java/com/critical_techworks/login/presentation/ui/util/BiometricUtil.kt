package com.critical_techworks.login.presentation.ui.util

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.critical_techworks.login.presentation.theme.BiometricDescription
import com.critical_techworks.login.presentation.theme.BiometricSubtitle
import com.critical_techworks.login.presentation.theme.BiometricTitle
import com.critical_techworks.login.presentation.theme.Cancel


class BiometricUtil {

    private fun hasBiometricCapability(context: Context): Int {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
    }

    fun isBiometricReady(context: Context) =
        hasBiometricCapability(context) == BiometricManager.BIOMETRIC_SUCCESS

    private fun setBiometricPromptInfo(
        title: String,
        subtitle: String,
        description: String,
    ): BiometricPrompt.PromptInfo {
        val builder = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setDescription(description)
            .setNegativeButtonText(Cancel)

        return builder.build()
    }

    private fun initBiometricPrompt(
        activity: AppCompatActivity,
        listener: BiometricAuthListener
    ): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(activity)

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                listener.onBiometricAuthenticationFailed(errorCode, errString.toString())
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                listener.onBiometricAuthenticationSuccess(result)
            }
        }

        return BiometricPrompt(activity, executor, callback)
    }

    fun showBiometricPrompt(
        title: String = BiometricTitle,
        subtitle: String = BiometricSubtitle,
        description: String = BiometricDescription,
        activity: AppCompatActivity,
        listener: BiometricAuthListener,
        cryptoObject: BiometricPrompt.CryptoObject? = null,
    ) {
        val promptInfo = setBiometricPromptInfo(
            title,
            subtitle,
            description,
        )

        val biometricPrompt = initBiometricPrompt(activity, listener)

        biometricPrompt.apply {
            if (cryptoObject == null) authenticate(promptInfo)
            else authenticate(promptInfo, cryptoObject)
        }
    }

    fun lunchBiometricSettings(context: Context) {
        ActivityCompat.startActivity(
            context,
            Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS),
            null
        )
    }
}
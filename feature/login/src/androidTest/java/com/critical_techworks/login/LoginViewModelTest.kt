package com.critical_techworks.login

import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.critical_techworks.login.presentation.ui.login.LoginViewModel
import com.critical_techworks.login.presentation.ui.util.BiometricAuthListener
import com.critical_techworks.login.presentation.ui.util.BiometricUtil
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {

    @get:Rule
    val instantTaskRule: TestRule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

    @RelaxedMockK
    lateinit var biometricUtil: BiometricUtil

    @RelaxedMockK
    lateinit var biometricAuthListener: BiometricAuthListener

    @RelaxedMockK
    val context = mockk<AppCompatActivity>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun when_biometricUtil_isBiometricReady_returns_false_biometricAuthListener_onBiometricAuthenticationNotFound_must_be_called() {
        every { biometricUtil.isBiometricReady(context) } answers { false }
        every { biometricUtil.showBiometricPrompt(any(), any(), any(), any(), any()) } answers {}
        every { biometricAuthListener.onBiometricAuthenticationNotFound() } just runs
        loginViewModel = LoginViewModel(biometricUtil = biometricUtil)
        loginViewModel.checkBiometricAuthentication(context, biometricAuthListener)
        verify { biometricAuthListener.onBiometricAuthenticationNotFound() }
    }

    @Test
    fun when_biometricUtil_isBiometricReady_returns_true_biometricUtil_showBiometricPrompt_must_be_called() {
        every { biometricUtil.isBiometricReady(context) } answers { true }
        every { biometricUtil.showBiometricPrompt(any(), any(), any(), any(), any()) } answers {}
        loginViewModel = LoginViewModel(biometricUtil = biometricUtil)
        loginViewModel.checkBiometricAuthentication(context, biometricAuthListener)
        verify { biometricUtil.showBiometricPrompt(any(), any(), any(), any(), any()) }
    }
}
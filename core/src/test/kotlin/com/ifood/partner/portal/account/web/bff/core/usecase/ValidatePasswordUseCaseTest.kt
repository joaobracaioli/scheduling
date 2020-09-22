package com.ifood.partner.portal.account.web.bff.core.usecase

import com.ifood.partner.portal.account.web.bff.core.usecase.account.ValidatePasswordUseCase
import com.ifood.partner.portal.core.result.ErrorType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidatePasswordUseCaseTest {
    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @BeforeEach
    fun setUp() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `should return true when strong password is sent`() {
        val strongPassword = "UltraMegaBlasterStrongPassword2831793812!"

        assertTrue(validatePasswordUseCase.execute(strongPassword).getResult())
    }

    @Test
    fun `should return only one message when password has multiple problems`() {
        val passwordWithoutUpperCaseAndDigits = "password_without_upper_case_and_digits"

        val resultError = validatePasswordUseCase.execute(passwordWithoutUpperCaseAndDigits)

        assertEquals("Password must contain 1 or more uppercase characters.", resultError.getResultError().description)
        assertEquals("400", resultError.getResultError().code)
        assertEquals(ErrorType.VALIDATION_ERROR, resultError.getResultError().errorType)
    }

    @Test
    fun `should return false when there are no upper case letters`() {
        val passwordWithoutUpperCase = "no_upper_case_password"

        val resultError = validatePasswordUseCase.execute(passwordWithoutUpperCase)

        assertEquals("Password must contain 1 or more uppercase characters.", resultError.getResultError().description)
        assertEquals("400", resultError.getResultError().code)
        assertEquals(ErrorType.VALIDATION_ERROR, resultError.getResultError().errorType)
    }

    @Test
    fun `should return false when there are no lower case letters`() {
        val passwordWithoutUpperCase = "NO_LOWER_CASE_LETTERS"

        val resultError = validatePasswordUseCase.execute(passwordWithoutUpperCase)

        assertEquals("Password must contain 1 or more lowercase characters.", resultError.getResultError().description)
        assertEquals("400", resultError.getResultError().code)
        assertEquals(ErrorType.VALIDATION_ERROR, resultError.getResultError().errorType)
    }

    @Test
    fun `should return false when password is smaller thant 8 characters`() {
        val passwordWithoutUpperCase = "Small12"

        val resultError = validatePasswordUseCase.execute(passwordWithoutUpperCase)

        assertEquals("Password must be 8 or more characters in length.", resultError.getResultError().description)
        assertEquals("400", resultError.getResultError().code)
        assertEquals(ErrorType.VALIDATION_ERROR, resultError.getResultError().errorType)
    }

    @Test
    fun `should return false when password has no numeric digits`() {
        val passwordWithoutUpperCase = "UltraMegaBlasterStrongPasswordWithoutDigits"

        val resultError = validatePasswordUseCase.execute(passwordWithoutUpperCase)

        assertEquals("Password must contain 1 or more digit characters.", resultError.getResultError().description)
        assertEquals("400", resultError.getResultError().code)
        assertEquals(ErrorType.VALIDATION_ERROR, resultError.getResultError().errorType)
    }
}

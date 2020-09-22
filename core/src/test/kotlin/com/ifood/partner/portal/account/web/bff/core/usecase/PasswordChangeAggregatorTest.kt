package com.ifood.partner.portal.account.web.bff.core.usecase

import com.ifood.partner.portal.account.web.bff.core.entities.utility.result.Result
import com.ifood.partner.portal.account.web.bff.core.usecase.account.PasswordChangeAggregator
import com.ifood.partner.portal.account.web.bff.core.usecase.account.PasswordChangeByTokenUseCase
import com.ifood.partner.portal.account.web.bff.core.usecase.account.PasswordChangeUseCase
import com.ifood.partner.portal.account.web.bff.core.usecase.account.ValidatePasswordUseCase
import com.ifood.partner.portal.account.web.bff.core.usecase.fixture.ErrorFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.UUID

class PasswordChangeAggregatorTest {

    private lateinit var passwordChangeAggregator: PasswordChangeAggregator

    @Mock
    private lateinit var passwordChangeByTokenUseCase: PasswordChangeByTokenUseCase

    @Mock
    private lateinit var passwordChangeUseCase: PasswordChangeUseCase

    @Mock
    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        passwordChangeAggregator = PasswordChangeAggregator(passwordChangeUseCase, passwordChangeByTokenUseCase, validatePasswordUseCase)
    }

    @Test
    fun `should always validate new password`() {
        val newPassword = "NewPassword123"
        val oldPassword = "Password123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()
        val mustChangePassword = true

        Mockito.`when`(validatePasswordUseCase.execute(newPassword))
            .thenReturn(Result.ok(true))
        Mockito.`when`(passwordChangeUseCase.execute(newPassword, accountId))
            .thenReturn(Result.ok(true))

        passwordChangeAggregator.execute(
            oldPassword,
            newPassword,
            email,
            accountId,
            mustChangePassword
        )

        verify(validatePasswordUseCase, times(1)).execute(newPassword)
    }

    @Test
    fun `should call identity directly when must change password is true`() {
        val newPassword = "NewPassword123"
        val oldPassword = "Password123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()
        val mustChangePassword = true

        Mockito.`when`(validatePasswordUseCase.execute(newPassword))
            .thenReturn(Result.ok(true))
        Mockito.`when`(passwordChangeUseCase.execute(newPassword, accountId))
            .thenReturn(Result.ok(true))

        passwordChangeAggregator.execute(
            oldPassword,
            newPassword,
            email,
            accountId,
            mustChangePassword
        )

        verify(passwordChangeUseCase, times(1)).execute(newPassword, accountId)
        verify(passwordChangeByTokenUseCase, times(0)).execute(accountId, email, oldPassword, newPassword)
    }

    @Test
    fun `should call kong access token to validate old password when must change password is false`() {
        val newPassword = "NewPassword123"
        val oldPassword = "Password123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()
        val mustChangePassword = false

        Mockito.`when`(validatePasswordUseCase.execute(newPassword))
            .thenReturn(Result.ok(true))
        Mockito.`when`(passwordChangeByTokenUseCase.execute(accountId, email, oldPassword, newPassword))
            .thenReturn(Result.ok(true))

        passwordChangeAggregator.execute(
            oldPassword,
            newPassword,
            email,
            accountId,
            mustChangePassword
        )

        verify(passwordChangeUseCase, times(0)).execute(newPassword, accountId)
        verify(passwordChangeByTokenUseCase, times(1)).execute(accountId, email, oldPassword, newPassword)
    }

    @Test
    fun `should return error when new password validation fail`() {
        val newPassword = "NewPassword123"
        val oldPassword = "Password123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()
        val mustChangePassword = false
        val weakPasswordErrorResult = ErrorFixture.createWeakPasswordErrorResult()

        Mockito.`when`(validatePasswordUseCase.execute(newPassword))
            .thenReturn(weakPasswordErrorResult)

        val aggregatorResult = passwordChangeAggregator.execute(
            oldPassword,
            newPassword,
            email,
            accountId,
            mustChangePassword
        )

        assertEquals(weakPasswordErrorResult.getResultError().code, aggregatorResult.getResultError().code)
        assertEquals(weakPasswordErrorResult.getResultError().description, aggregatorResult.getResultError().description)
    }
}

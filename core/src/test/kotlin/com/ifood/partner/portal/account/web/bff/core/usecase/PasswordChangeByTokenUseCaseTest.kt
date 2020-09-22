package com.ifood.partner.portal.account.web.bff.core.usecase

import com.ifood.partner.portal.account.web.bff.core.client.identity.IdentityGateway
import com.ifood.partner.portal.account.web.bff.core.client.kong.KongGateway
import com.ifood.partner.portal.account.web.bff.core.entities.utility.result.Result
import com.ifood.partner.portal.account.web.bff.core.usecase.account.PasswordChangeByTokenUseCase
import com.ifood.partner.portal.account.web.bff.core.usecase.fixture.AccessTokenFixture
import com.ifood.partner.portal.account.web.bff.core.usecase.fixture.ErrorFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.UUID

class PasswordChangeByTokenUseCaseTest {

    private lateinit var passwordChangeByTokenUseCase: PasswordChangeByTokenUseCase

    @Mock
    private lateinit var identityGateway: IdentityGateway

    @Mock
    private lateinit var kongGateway: KongGateway

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        passwordChangeByTokenUseCase = PasswordChangeByTokenUseCase(identityGateway, kongGateway)
    }

    @Test
    fun `should always validate old password when must change password attribute is false`() {
        val newPassword = "NewPassword123"
        val oldPassword = "Password123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()

        Mockito.`when`(kongGateway.login(email, oldPassword))
            .thenReturn(Result.ok(AccessTokenFixture.createSuccessfulAccessToken()))
        Mockito.`when`(identityGateway.changePassword(newPassword, accountId))
            .thenReturn(Result.ok(true))

        passwordChangeByTokenUseCase.execute(
            accountId,
            email,
            oldPassword,
            newPassword
        )
        verify(kongGateway, Mockito.times(1)).login(email, oldPassword)
        verify(identityGateway, Mockito.times(1)).changePassword(newPassword, accountId)
    }

    @Test
    fun `should throw 403 when old password validation fails`() {
        val newPassword = "NewPassword123"
        val oldPassword = "InvalidPassword123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()
        val forbiddenErrorResponse = ErrorFixture.createPasswordChangeErrorResult()

        Mockito.`when`(kongGateway.login(email, oldPassword))
            .thenReturn(forbiddenErrorResponse)
        Mockito.`when`(identityGateway.changePassword(newPassword, accountId))
            .thenReturn(Result.ok(true))

        val useCaseResponse = passwordChangeByTokenUseCase.execute(
            accountId,
            email,
            oldPassword,
            newPassword
        )
        verify(kongGateway, Mockito.times(1)).login(email, oldPassword)
        verify(identityGateway, Mockito.times(0)).changePassword(newPassword, accountId)
        assertEquals(forbiddenErrorResponse.getResultError().code, useCaseResponse.getResultError().code)
        assertEquals(forbiddenErrorResponse.getResultError().description, useCaseResponse.getResultError().description)
        assertEquals(forbiddenErrorResponse.getResultError().errorType, useCaseResponse.getResultError().errorType)
    }

    @Test
    fun `should throw 503 when identity fails to respond`() {
        val newPassword = "NewPassword123"
        val oldPassword = "Password123"
        val email = "joao.henri+2@ifood.com.br"
        val accountId = UUID.randomUUID()
        val unavailableErrorResponse = ErrorFixture.createServiceUnavailableErrorResult()

        Mockito.`when`(kongGateway.login(email, oldPassword))
            .thenReturn(Result.ok(AccessTokenFixture.createSuccessfulAccessToken()))
        Mockito.`when`(identityGateway.changePassword(newPassword, accountId))
            .thenReturn(unavailableErrorResponse)

        val useCaseResponse = passwordChangeByTokenUseCase.execute(
            accountId,
            email,
            oldPassword,
            newPassword
        )
        verify(kongGateway, Mockito.times(1)).login(email, oldPassword)
        verify(identityGateway, Mockito.times(1)).changePassword(newPassword, accountId)
        assertEquals(unavailableErrorResponse.getResultError().code, useCaseResponse.getResultError().code)
        assertEquals(unavailableErrorResponse.getResultError().description, useCaseResponse.getResultError().description)
        assertEquals(unavailableErrorResponse.getResultError().errorType, useCaseResponse.getResultError().errorType)
    }
}

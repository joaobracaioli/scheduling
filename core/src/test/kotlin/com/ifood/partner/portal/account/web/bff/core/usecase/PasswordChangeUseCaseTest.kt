package com.ifood.partner.portal.account.web.bff.core.usecase

import com.ifood.partner.portal.account.web.bff.core.client.identity.IdentityGateway
import com.ifood.partner.portal.account.web.bff.core.entities.utility.result.Result
import com.ifood.partner.portal.account.web.bff.core.usecase.account.PasswordChangeUseCase
import com.ifood.partner.portal.account.web.bff.core.usecase.fixture.ErrorFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.UUID

class PasswordChangeUseCaseTest {

    private lateinit var passwordChangeUseCase: PasswordChangeUseCase

    @Mock
    private lateinit var identityGateway: IdentityGateway

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        passwordChangeUseCase = PasswordChangeUseCase(identityGateway)
    }

    @Test
    fun `should directly call identity when on must change password flow`() {
        val newPassword = "NewPassword123"
        val accountId = UUID.randomUUID()

        `when`(identityGateway.changePassword(newPassword, accountId))
            .thenReturn(Result.ok(true))

        passwordChangeUseCase.execute(
            newPassword,
            accountId
        )
        verify(identityGateway, times(1)).changePassword(newPassword, accountId)
    }

    @Test
    fun `should throw 503 when identity fails to respond`() {
        val newPassword = "NewPassword123"
        val accountId = UUID.randomUUID()

        `when`(identityGateway.changePassword(newPassword, accountId))
            .thenReturn(ErrorFixture.createServiceUnavailableErrorResult())

        val useCaseResponse = passwordChangeUseCase.execute(
            newPassword,
            accountId
        )
        verify(identityGateway, times(1)).changePassword(newPassword, accountId)
        assertEquals(ErrorFixture.createServiceUnavailableErrorResult().getResultError().code, useCaseResponse.getResultError().code)
    }
}

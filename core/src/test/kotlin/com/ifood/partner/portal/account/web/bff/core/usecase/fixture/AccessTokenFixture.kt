package com.ifood.partner.portal.account.web.bff.core.usecase.fixture

import com.ifood.partner.portal.account.web.bff.core.entities.account.accesstoken.AccessToken
import java.util.Optional

class AccessTokenFixture {
    companion object {
        fun createSuccessfulAccessToken(): AccessToken {
            return AccessToken(
                accessToken = Optional.of("SomeToken"),
                tokenType = Optional.of("bearer"),
                expiresIn = Optional.of(21999L),
                scope = Optional.of("trust read shop"),
                createdAt = Optional.of(1597172271L),
                error = Optional.empty(),
                errorDescription = Optional.empty()
            )
        }
    }
}

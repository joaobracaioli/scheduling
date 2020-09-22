package com.ifood.partner.portal.account.web.bff.core.usecase.fixture

import com.ifood.partner.portal.account.web.bff.core.entities.account.accesstoken.AccessToken
import com.ifood.partner.portal.account.web.bff.core.entities.utility.result.Result
import com.ifood.partner.portal.core.result.ErrorType
import com.ifood.partner.portal.core.result.ErrorType.UNAVAILABLE
import com.ifood.partner.portal.core.result.ErrorType.VALIDATION_ERROR
import com.ifood.partner.portal.core.result.ResultError

class ErrorFixture {
    companion object {
        private const val CHANGE_PASSWORD_ERROR = "Failed to change password."
        private const val CHANGE_PASSWORD_ERROR_CODE = "error.user.change-password"
        private const val PASSWORD_VALIDATION_ERROR = "Failed to change password, the new password is not valid."

        fun createPasswordChangeErrorResult(): Result<AccessToken> {
            return Result.error(ResultError.builder()
                .errorType(ErrorType.FORBIDDEN)
                .description(CHANGE_PASSWORD_ERROR)
                .code(CHANGE_PASSWORD_ERROR_CODE)
                .build())
        }

        fun createServiceUnavailableErrorResult(): Result<Boolean> {
            return Result.error(ResultError.builder()
                .errorType(UNAVAILABLE)
                .code(UNAVAILABLE.httpCode().toString())
                .description(UNAVAILABLE.name)
                .build())
        }

        fun createWeakPasswordErrorResult(): Result<Boolean> {
            return Result.error(ResultError.builder()
                .errorType(VALIDATION_ERROR)
                .description(PASSWORD_VALIDATION_ERROR)
                .build())
        }
    }
}
